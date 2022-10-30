from PIL import Image
from torch.utils.tensorboard import SummaryWriter
from torchvision import transforms

writer=SummaryWriter("logs")
'''
！！！python文件中写的相对路径是相对本python文件的路径；terminal中的相对路径是相对根目录的路径
'''
img=Image.open("images/Snipaste_2022-09-22_11-41-37.jpg")
print(img)

# ToTensor的使用
trans_totensor=transforms.ToTensor()
img_tensor=trans_totensor(img)
writer.add_image("ToTensor",img_tensor)
writer.close()

# Normalize
print(img_tensor[0][0][0])
'''
因为图片是三信道RGB，所以要提供三个均值，三个标准差
网友：0.5的值，是假设数值在[0,1]中呈现高斯分布
'''
trans_norm=transforms.Normalize([0.5,0.5,0.5],[0.5,0.5,0.5])
'''
网友问：这里CTRL+P怎么不显示类型？
网友：原来Normalize类没有call了，我说怎么干按Ctrl+P没有显示呢
'''
img_norm=trans_norm(img_tensor)
print(img_norm[0][0][0])
writer.add_image("Normalize",img_norm)

# Resize
print(img.size)
tran_resize=transforms.Resize((512,512))
'''
这里直接使用了PIL格式的图片：img PIL->resize->img_resize PIL
'''
img_resize=tran_resize(img)
print(img_resize)
'''
想要把图片在tensorboard进行显示的话，需要把PIL图片转化成tensor格式的图片:img_resize PIL->totensor->img_resize tensor

返回结果把之前的img_resize覆盖了
'''
img_resize=trans_totensor(img_resize)
writer.add_image("Resize",img_resize,0)

# Compose - resize - 2
trans_resize_2=transforms.Resize(512)
'''
PIL->PIL->tensor
'''
trans_compose=transforms.Compose([trans_resize_2,trans_totensor])
'''
compose的输入==compose的第一个参数的输入
'''
img_resize_2=trans_compose(img)
writer.add_image("Resize",img_resize_2,1)

# RandomCrop
trans_random=transforms.RandomCrop(512)
trans_compose_2=transforms.Compose([trans_random,trans_totensor])
'''
随机把图片裁剪为512*512的大小
'''
for i in range(10):
    img_crop=trans_compose_2(img)
    writer.add_image("RandomCrop",img_crop,i)

writer.close()