from torch.utils.data import Dataset, DataLoader
# import cv2 #一般用这个来处理图片，但是老师这没下载吗，所以使用了不太推荐的PIL来处理图片
import numpy as np
from PIL import Image
import os
from torchvision import transforms
from torch.utils.tensorboard import SummaryWriter
from torchvision.utils import make_grid

writer = SummaryWriter("logs")

class MyData(Dataset):# 括号中写Dataset表示MyData继承了Dataset类

    '''
    这是初始化类时需要运行的函数

    一般是为整个class提供一个全局变量，主要四为后面的函数（在本例中即为 getitem next）提供他们需要的一些量

    所以额这个方法可以留到最后再写，可以先尝试写其他方法
    '''
    def __init__(self, root_dir, image_dir, label_dir, transform):
        self.root_dir = root_dir
        self.image_dir = image_dir
        self.label_dir = label_dir
        self.label_path = os.path.join(self.root_dir, self.label_dir)
        self.image_path = os.path.join(self.root_dir, self.image_dir)
        self.image_list = os.listdir(self.image_path)
        self.label_list = os.listdir(self.label_path)
        self.transform = transform
        # 因为label 和 Image文件名相同，进行一样的排序，可以保证取出的数据和label是一一对应的
        self.image_list.sort()
        self.label_list.sort()

    def __getitem__(self, idx):
        img_name = self.image_list[idx]
        label_name = self.label_list[idx]
        '''
        我：问地址斜杠的因为一个是相对路径一个是绝对路径；据我观察，绝对路径需要防止转义，相对路径不需要防止转义，因为相对路径用/表示层级
        网友：担心转义的话，路径引号前加个r就行了，防转义
        老师：windows和linux中 / 是不同含义的，使用os.path.join，那么python就可以根据系统自动把根目录拼接上image 或 label目录且不会出错
        '''
        img_item_path = os.path.join(self.root_dir, self.image_dir, img_name)
        label_item_path = os.path.join(self.root_dir, self.label_dir, label_name)
        img = Image.open(img_item_path)

        with open(label_item_path, 'r') as f:
            label = f.readline()

        # img = np.array(img)
        img = self.transform(img)
        sample = {'img': img, 'label': label}
        return sample

    def __len__(self):
        assert len(self.image_list) == len(self.label_list)
        return len(self.image_list)

if __name__ == '__main__':
    transform = transforms.Compose([transforms.Resize((256, 256)), transforms.ToTensor()])
    root_dir = "dataset/train"
    image_ants = "ants_image"
    label_ants = "ants_label"
    ants_dataset = MyData(root_dir, image_ants, label_ants, transform)
    image_bees = "bees_image"
    label_bees = "bees_label"
    bees_dataset = MyData(root_dir, image_bees, label_bees, transform)
    '''
    网友：python的+是拼接 但是多了就麻烦了。因为继承了Dataset类，相加是内置的功能，所以不用再手动修改__add__了。
    '''
    train_dataset = ants_dataset + bees_dataset

    # transforms = transforms.Compose([transforms.Resize(256, 256)])
    dataloader = DataLoader(train_dataset, batch_size=1, num_workers=2)

    writer.add_image('error', train_dataset[119]['img'])
    writer.close()
    # for i, j in enumerate(dataloader):
    #     # imgs, labels = j
    #     print(type(j))
    #     print(i, j['img'].shape)
    #     # writer.add_image("train_data_b2", make_grid(j['img']), i)
    #
    # writer.close()
