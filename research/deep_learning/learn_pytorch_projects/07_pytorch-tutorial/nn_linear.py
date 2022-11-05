import torch
import torchvision
from torch import nn
from torch.nn import Linear
from torch.utils.data import DataLoader

dataset = torchvision.datasets.CIFAR10("./data", train=False, transform=torchvision.transforms.ToTensor(),
                                       download=True)

dataloader = DataLoader(dataset, batch_size=64, drop_last=True)

class Tudui(nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()
        '''
        把inputfeature转化为outputfeature
        '''
        self.linear1 = Linear(196608, 10)

    def forward(self, input):
        output = self.linear1(input)
        return output

tudui = Tudui()

for data in dataloader:
    imgs, targets = data
    print(imgs.shape)
    '''
    torch.reshape(img,(1,1,1,-1)) 会把torch.size([64,3,32,32])转化成一个batch里一张图片 一个channel 长为1 宽为x（torch自己计算 为 64*3*32*32）
        我：我觉得实际上训练图片是不可能这么转化，这样子相当于把64张图片整合为1张图片，但是我们实际是要针对每张图片做处理；这里这么操作估计是只是为了讲解线性层
    
    flatten也是为了把图片铺平。但是输出的是torch.Size([196608])，而不是reshapoe后输出的torch.Size([1,1,1,196608])；相当于flatten在reshape的基础上还有降维的功能（去除掉无用的嵌套括号）
    
    reshape功能更强大，可以指定尺寸进行变换；flatten的话只是把它变成一行
    '''
    output = torch.flatten(imgs)
    print(output.shape)# 打印196608
    output = tudui(output)
    print(output.shape)# 打印10；我理解：相当于把feature融合了