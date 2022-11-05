import torchvision.datasets

from torch.utils.data import DataLoader

# 准备的测试数据集
from torch.utils.tensorboard import SummaryWriter

'''
我们选择拿测试集，因为测试集样本少，拿到结果比较快

CIFAR10之前在learn_torchvision_dataset已经下载，把root设置为已经下载好的数据集，就可以避免重新下载；注意这里的路径写的是相对本python文件的相对路径

因为我们加载的是测试集，所以train为false

因为CIFAR本身是PILimage，所以transform需要用totensor转化为tensor数据类型

准备好测试数据集后，就可以学习dataloader了
'''
test_data=torchvision.datasets.CIFAR10("../05_learn_torchvision_dataset/dataset",train=False,transform=torchvision.transforms.ToTensor())

test_loader=DataLoader(dataset=test_data,batch_size=4,shuffle=True,num_workers=0,drop_last=False)

# 测试数据集中第一张图片及target
'''
可以看到图片大小是三channel的图片，target即分类标签为3。输出如下：
torch.Size([3, 32, 32])
3
'''
img,target=test_data[0]
print(img.shape)
print(target)

# 使用dataloader来以batch为大小加载数据
'''
输出如下：
torch.Size([4, 3, 32, 32])
tensor([3, 2, 4, 1])
torch.Size([4, 3, 32, 32])
tensor([7, 4, 1, 9])
torch.Size([4, 3, 32, 32])
tensor([9, 1, 2, 1])
torch.Size([4, 3, 32, 32])
tensor([5, 3, 9, 2])
torch.Size([4, 3, 32, 32])
tensor([2, 4, 4, 9])
torch.Size([4, 3, 32, 32])
tensor([3, 7, 3, 6])
。。。

可以看到：
1. dataset中第一张图片分类是3，但是dataloader加载的一个图片的分类不是3，说明shuffule起作用了
2. 可以看到a_batch_data中，有四张三通道的32*32的图片，分别对应四个标签
'''
for a_batch_data in test_loader:
    imgs,targets=a_batch_data
    print(imgs.shape)
    print(targets)

# 使用dataloader把数据加载到tensorboard中
writer = SummaryWriter("dataloader")
step=0
for a_batch_data in test_loader:
    imgs,targets=a_batch_data
    writer.add_images("test_data",imgs,step)
    step=step+1

# 可以在testloader外面加一层epoch，这样可以就是真实情况下投喂神经网络
for epoch in range(2):
    step = 0
    for a_batch_data in test_loader:
        imgs, targets = a_batch_data
        writer.add_images("Epoch:{}".format(epoch), imgs, step)
        step = step + 1

writer.close()