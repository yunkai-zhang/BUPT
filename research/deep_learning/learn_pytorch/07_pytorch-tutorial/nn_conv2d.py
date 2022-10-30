# -*- coding: utf-8 -*-
import torch
import torchvision
from torch import nn
from torch.nn import Conv2d
from torch.utils.data import DataLoader
from torch.utils.tensorboard import SummaryWriter

'''
我们这里取测试数据集，因为训练数据集太大了，所以train设置为false
'''
dataset = torchvision.datasets.CIFAR10("./data", train=False, transform=torchvision.transforms.ToTensor(),
                                       download=True)
dataloader = DataLoader(dataset, batch_size=64)

class Tudui(nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()# 完成父类的初始化
        '''
        要记得使用self，这样self后面的变量在其他函数中也可以使用到
        
        因为是彩色图像，所以inchannel设置为3；outchannel设置为6层试试看；stride和padding按照默认值也显式设置一下
        '''
        self.conv1 = Conv2d(in_channels=3, out_channels=6, kernel_size=3, stride=1, padding=0)

    '''
    输入经过卷积后输出
    '''
    def forward(self, x):
        x = self.conv1(x)
        return x

# 初始化网络
tudui = Tudui()

writer = SummaryWriter("./logs")

# 把每个batch处理一下
step = 0
for data in dataloader:
    imgs, targets = data
    output = tudui(imgs)

    '''
    卷积前 torch.Size([64, 3, 32, 32])
    卷积后 torch.Size([64, 6, 30, 30])
    因为卷积的作用，所以从32*32变成30*30，channel也变成了Conv2d中outchannel设置好的6
    '''
    print(imgs.shape)
    print(output.shape)
    # shape为 torch.Size([64, 3, 30, 30])
    writer.add_images("input", imgs, step)
    '''
    shape转变 torch.Size([64, 6, 30, 30])  -> [xxx, 3, 30, 30]；因为tensorboard不支持展示6channel的图片，所以要把它转化为3channel。
    
    老师：batchsize填-1，这样子就会自动根据 3 30 30平铺后的结果计算batchsize
    网友：相当于平铺了.
    '''
    output = torch.reshape(output, (-1, 3, 30, 30))
    writer.add_images("output", output, step)

    step = step + 1

writer.close()
