from PIL import Image
from torch.utils.tensorboard import SummaryWriter
from torchvision import transforms

'''
python的用法->tensor的数据类型

通过transforms.ToTensor去看两个问题
1，transforms该如何使用（python）
2，为什么我们需要Tensor数据类型
'''

'''
针对同一张图片：
绝对路径：D:\CodeProjects\GitHub\BUPT\research\deep_learning\learn_pytorch\dataset\train\ants\0013035.jpg
相对路径：../dataset/train/ants/0013035.jpg
可以看到，windows中绝对路径的反斜杠是特殊字符(即转义符本身)，需要转义符转义，没办法当做很好的字符串了；但是相对路径的的斜杠是不需要转义的。
所以我们尽量使用相对路径
'''
img_path= "../01_learn_dataset/dataset/train/ants/0013035.jpg"
img=Image.open(img_path)

writer=SummaryWriter("logs")

# 1，transforms该如何使用（python）
tensor_trans=transforms.ToTensor()
tensor_img=tensor_trans(img)

writer.add_image("Tensor_img",tensor_img)

# 和java类似，使用输入输出后要及时关闭
writer.close()



