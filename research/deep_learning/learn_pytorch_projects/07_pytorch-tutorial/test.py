# -*- coding: utf-8 -*-
# 从test.py文件找到imgs/dog/jpg文件，这考察的是相对路径的概念，大家一定要熟悉
import torch
import torchvision
from PIL import Image
from torch import nn

# image_path = "./imgs/dog.jpg"
image_path = "./imgs/airplane.jpg"
# 这里使用了pil来读取图片
image = Image.open(image_path)
print(image)
'''
resize的话，输入是pil或tensor格式，输出格式与输入保持一致；所以输入了pil格式，想得到输出为tensor的话，可以使用totensor

因为输入的测试用的图片的尺寸不是32*32，但是训练得到的模型只接收32*32尺寸的图片，所以要把输入图片转化为32*32的样式

image.convert('RGB') 是因为png的通道数是4，所以转化为RGB只保留图片的颜色通道，这样才和模型期待的输入图片的通道数相符；如果图片本来就是jpg格式，那么本操作不改变图片。
'''
image = image.convert('RGB')
transform = torchvision.transforms.Compose([torchvision.transforms.Resize((32, 32)),
                                            torchvision.transforms.ToTensor()])

image = transform(image)
print(image.shape)

'''
网友：实测，不用在复制粘贴神经网络，也可以用！！！！！！
- 网友反驳：粘贴的是参数，你需要把参数放入到网络模型中去，没有模型你只放参数是不可能运行的
- 网友：这加载的是训练过的参数吧
- 高赞网友：将神经网络的代码赋值过来是为了告诉解释器这个神经网络的组成部分，与加载参数无关
- 网友：使用方法一加载自己创建的网络结构时，光写这句会报错，需要把自己创建的网络也copy过来,或者把save文件中的都导入
'''
class Zhangyun(nn.Module):
    def __init__(self):
        super(Zhangyun, self).__init__()
        self.model = nn.Sequential(
            nn.Conv2d(3, 32, 5, 1, 2),
            nn.MaxPool2d(2),
            nn.Conv2d(32, 32, 5, 1, 2),
            nn.MaxPool2d(2),
            nn.Conv2d(32, 64, 5, 1, 2),
            nn.MaxPool2d(2),
            nn.Flatten(),
            nn.Linear(64*4*4, 64),
            nn.Linear(64, 10)
        )

    def forward(self, x):
        x = self.model(x)
        return x

# 如果使用GPU训练的模型，要在cpu上使用的话，需要maplocation来映射一下
model = torch.load("zhangyun_9.pth", map_location=torch.device('cpu'))
print(model)

'''
土堆：因为图片没有指定batchsize，所以这里要reshape来增加一个维度
网友：mat1和mat2相乘报错的同学可以检查下是不是reshape时没赋值
'''
image = torch.reshape(image, (1, 3, 32, 32))
'''
下面两行不要忘了，养成良好的代码习惯！
'''
model.eval()
with torch.no_grad():
    output = model(image)
print(output)

'''
针对dog的图片，预测结果是7，比5大一点，错误，但是5是dog为正确预测，这是因为模型的训练轮数很小；但其实也能看出模型也认为本图很像狗
- 网友：主要是这个模型神经层数太少了吧
- 网友：我训练了50次，准确率只在60不动了
    - 网友：因为梯度消失啊，后面你会发现梯度迟迟难以变化
    - 网友：我把每步的数据集都改成随机选取顺便把dropout开了，每步曲线稳定了不少
    - 网友：加上relu函数，20次准确率69
- 土堆：可以使用gpu版本训练的更长时间的模型，这样预测的准确度会更高
- 网友：识别正确率低的原因：我估计是池化和卷积的时候，损失了很多信息，其次本身训练集只有32*32，太模糊了，数据量小
    - 网友：主要原因没加relu层，其实整个模型就是个线性的

针对 airplane的图片，预测结果是0，正确！
- 我：应该是因为飞机的特征比较明显，不像狗和马可能还有相似点，飞机和其他物体都长得不太像。

注意：使用argmax可以把输出转化为利于解读的样式
'''
print(output.argmax(1))