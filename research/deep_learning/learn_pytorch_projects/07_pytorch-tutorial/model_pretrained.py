# -*- coding: utf-8 -*-
import torchvision

'''
网友：模型你可以理解为参数训练好的网络
土堆：在填函数的输入的时候， ctrl+p 就可以给提示让设置下一个参数。
土堆：这个数据集现在不能公开下载，得去网上自己找资源；而且这个数据集太大了，有100多个g，要慢慢下载。因为数据集太大，没办法通过这个模型验证vgg的pretrained参数
'''
# train_data = torchvision.datasets.ImageNet("../data_image_net", split='train', download=True,
#                                            transform=torchvision.transforms.ToTensor())
from torch import nn

'''
网友：VGG系列应该是最大的模型了
土堆：pretrained=True时需要进行下载训练好的权重
网友：pretrained为false的 应该是随机初始化之后的，而一般随机初始化不能设置为0,0附近就行，吴恩达深度学习讲过
'''
vgg16_false = torchvision.models.vgg16(pretrained=False)
vgg16_true = torchvision.models.vgg16(pretrained=True)

'''
这里可以看到vgg16的结构，可以看到输出的维度是1000，所以可以分类出1000个物体。
'''
print(vgg16_true)

'''
vgg的输出是1000维度，想应用于10维度分类的cifar，有很多种方案：
1. 把vgg的输出层从1000维度，改成10维度
2. 在1000维度的线性输出层下面再加一层，让它的输入是1000，输出是10

土堆：一般用vgg16作为前置的网络使用，来提取一些特征
    - 网友：高情商的说法叫迁移学习
    - 网友：只有在分类相近的情况下才比较有用，不能乱迁移
    - 网友：这种做法只适合前后部分的修改，想要跳层或者跨层连接的话，就只能自己写
'''
train_data = torchvision.datasets.CIFAR10('../data', train=True, transform=torchvision.transforms.ToTensor(),
                                          download=True)

'''
这里采用的是第二种方式，给模型添加module，把1000维度的输出映射为10维度的输出：
- 在分类器sequantial的后面加上模型可以用：vgg16_true.add_module('add_linear', nn.Linear(1000, 10))
- 如果想在sequantial内部的后方加上模型，可以用下面的写法，指定sequantial的名字即classifier，这样就在sequantial中加了一个module

网友问：加了一层后是不是需要再训练？
'''
vgg16_true.classifier.add_module('add_linear', nn.Linear(1000, 10))
print(vgg16_true)

'''
这里采用的是第一种方式，给模型替换module，把4096->1000维度的输出映射为4096->10维度的输出：
如下可以把指定sequantial的指定module替换为新的module
'''
print(vgg16_false)
vgg16_false.classifier[6] = nn.Linear(4096, 10)
print(vgg16_false)