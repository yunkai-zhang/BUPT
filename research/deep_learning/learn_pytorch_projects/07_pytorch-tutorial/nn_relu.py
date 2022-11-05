# -*- coding: utf-8 -*-

import torch
import torchvision
from torch import nn
from torch.nn import ReLU, Sigmoid
from torch.utils.data import DataLoader
from torch.utils.tensorboard import SummaryWriter

input = torch.tensor([[1, -0.5],
                      [-1, 3]])

'''
网友问：relu函数也没有对输入维度有要求啊，为什么要变成个四维的矩阵？
    网友答：第一个维度是batch size第二个是channels输入必须有四个维度
    网友答：1.10版本不需要指定batch_size 了
    高赞网友：只要让四个参数的积等于input元素数就行，通道数-1可以帮你自动算好
'''
input = torch.reshape(input, (-1, 1, 2, 2))
print(input.shape)

dataset = torchvision.datasets.CIFAR10("./data", train=False, download=True,
                                       transform=torchvision.transforms.ToTensor())

dataloader = DataLoader(dataset, batch_size=64)

class Tudui(nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()
        self.relu1 = ReLU()
        self.sigmoid1 = Sigmoid()

    def forward(self, input):
        '''
        relu对图像的处理作用不明显，因为relu在x正半轴上是x=y，相当于直接输出输入值；所以我们使用sigmoid函数来查看激活函数对图片的影响
        高赞网友：ReLU激活函数的功能就是这个，小于0的数返回0，不小于0的数返回原值
        网友：激活函数，是将神经网络的线性模型变换成非线性的；说了非线性激活作用是提高泛化能力
        '''
        output = self.sigmoid1(input)
        return output

tudui = Tudui()

writer = SummaryWriter("./logs_relu")
step = 0
for data in dataloader:
    imgs, targets = data
    '''
    网友问：一直都不太懂，这是以step的方式逐个读取target图片进行处理吗，为什么非要用循环
        网友答：使用循环可以让step每次数据改变，这样在board中显示就可以都显示出来，只不过在不同的step中，如果不改变step那么可能会覆盖，只显示一版数据这样
    '''
    writer.add_images("input", imgs, global_step=step)
    output = tudui(imgs)
    writer.add_images("output", output, step)
    step += 1

writer.close()