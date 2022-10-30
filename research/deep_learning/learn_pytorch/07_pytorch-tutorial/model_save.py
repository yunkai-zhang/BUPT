# -*- coding: utf-8 -*-
import torch
import torchvision
from torch import nn

# 这里pretrained设置为false，说明使用的模型的参数是没有训练的，是仅仅初始化过的。
vgg16 = torchvision.models.vgg16(pretrained=False)
'''
保存方式1,模型结构+模型参数:
1. 模型的后缀随便写什么都可以，推荐写pth作为后缀。
2. 这种方式把模型的结构和参数都保存下来了。
'''
torch.save(vgg16, "vgg16_method1.pth")

'''
# 保存方式2，模型参数（官方推荐）
1, 相当于把vgg的参数保存为字典，现在就没有保存vgg的结构了
2，对于比较大的模型，这种保存方式需要的数据空间更小
'''
torch.save(vgg16.state_dict(), "vgg16_method2.pth")

# 陷阱
class Tudui(nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()
        self.conv1 = nn.Conv2d(3, 64, kernel_size=3)

    def forward(self, x):
        x = self.conv1(x)
        return x

tudui = Tudui()
torch.save(tudui, "tudui_method1.pth")