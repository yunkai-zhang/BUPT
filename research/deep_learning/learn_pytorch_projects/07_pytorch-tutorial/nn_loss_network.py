# -*- coding: utf-8 -*-
# 在神经网络中应用损失函数
import torchvision
from torch import nn
from torch.nn import Sequential, Conv2d, MaxPool2d, Flatten, Linear
from torch.utils.data import DataLoader

'''
网友：做自己的数据集需要先去网上下载图片，然后利用工具自己手动标注
网友：我也想问自己的数据集怎么导入，这个cifar10它已经给你处理好了都

我：下载到../data的话，所有模块就能共用cifar数据集
'''
dataset = torchvision.datasets.CIFAR10("../data", train=False, transform=torchvision.transforms.ToTensor(),
                                       download=True)

dataloader = DataLoader(dataset, batch_size=1)

class Tudui(nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()
        self.model1 = Sequential(
            Conv2d(3, 32, 5, padding=2),
            MaxPool2d(2),
            Conv2d(32, 32, 5, padding=2),
            MaxPool2d(2),
            Conv2d(32, 64, 5, padding=2),
            MaxPool2d(2),
            Flatten(),
            Linear(1024, 64),
            Linear(64, 10)
        )

    def forward(self, x):
        x = self.model1(x)
        return x


loss = nn.CrossEntropyLoss()
tudui = Tudui()
for data in dataloader:
    '''
    这里可以打印imgs和targets看看数据长什么样子，进而可以准确使用损失函数。
    '''
    imgs, targets = data
    outputs = tudui(imgs)
    result_loss = loss(outputs, targets)
    '''
    使用反向传播，这样在debug的时候，参数栏的weight下面就能看到grad不为空
    '''
    result_loss.backward()
    print("ok")