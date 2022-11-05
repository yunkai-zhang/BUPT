# -*- coding: utf-8 -*-
# 神经网络中使用优化器来下降梯度
import torch
import torchvision
from torch import nn
from torch.nn import Sequential, Conv2d, MaxPool2d, Flatten, Linear
from torch.optim.lr_scheduler import StepLR
from torch.utils.data import DataLoader

'''
这里设置transfrom来把数据集转化成tensor数据类型
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
'''
使用随机梯度下降做优化器：
1. lr学习速率一般开始用比较大的，后面用比较小的，学习率衰减
'''
# 这里定义了优化器
optim = torch.optim.SGD(tudui.parameters(), lr=0.01)
for epoch in range(20):
    running_loss = 0.0
    for data in dataloader:
        imgs, targets = data
        outputs = tudui(imgs)
        # 先得到loss，准备
        result_loss = loss(outputs, targets)
        # 优化器把历史保存的梯度清零
        optim.zero_grad()
        # 根据loss，得到了每个可调节参数对应的梯度
        result_loss.backward()
        # 根据每个参数对应的梯度对每个参数进行调优
        optim.step()
        running_loss = running_loss + result_loss
    # 网友：学习率太高了的话loss就会往上走了，所以要使用梯度衰减
    print(running_loss)