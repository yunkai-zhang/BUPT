# -*- coding: utf-8 -*-
import torch
'''
这一步就是引入所有自己建立的模型，避免到处复制模型结构
'''
from model_save import *
import torchvision
from torch import nn

# 方式1-》保存方式1，加载模型
'''
网友：没有预训练的模型不是没有参数，而是参数是初始化的状态
'''
model = torch.load("vgg16_method1.pth")
# print(model)

# 方式2，加载模型
vgg16 = torchvision.models.vgg16(pretrained=False)# 先把裸模型加载出来
vgg16.load_state_dict(torch.load("vgg16_method2.pth"))# 在把保存的参数赋值给裸模型
# model = torch.load("vgg16_method2.pth") # 把model打印出来就可以看到第二种方式保存的模型只有模型的参数
# print(vgg16)

# 陷阱1
'''
load自定义的模型的时候，要把Tudui类也放进执行load的类中
- 网友：从别的文件import过来就可以了
    - 网友问：import 的话训练好的模型的随机参数或者其他什么参数会改变的妈？
    - 网友：1.12已经不用引入了
    - 土堆：是的，真实写项目的时候不会把模型导出复制，而是直接在一个文件里写好模型，然后在需要的文件中import一下
'''
# class Tudui(nn.Module):
#     def __init__(self):
#         super(Tudui, self).__init__()
#         self.conv1 = nn.Conv2d(3, 64, kernel_size=3)
#
#     def forward(self, x):
#         x = self.conv1(x)
#         return x

model = torch.load('tudui_method1.pth')
print(model)