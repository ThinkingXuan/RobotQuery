import os
import random

from pythonRobot.test4.word_segment import segment

conv_path = 'prisonb.conv'

if not os.path.exists(conv_path):
    print("数据集不存在")
    exit()

convs = []  # 对话集合

with open(conv_path, 'r', encoding='utf-8') as f:
    one_conv = []  # 一次完整的对话
    for line in f:
        line = line.strip('\n')
        if line == '':
            continue
        if line[0] == 'E':
            if one_conv:
                convs.append(one_conv)
            one_conv = []

        elif line[0] == 'M':
            one_conv.append(line.split(' ')[1])

# print(convs[:3])


#  把对话分成问和答

ask = []  # 问
response = []  # 答

for conv in convs:
    if len(conv) == 1:
        continue
    if len(conv) % 2 != 0:  # 奇数对话数,转为偶数对话
        conv = conv[:-1]

    for i in range(len(conv)):
        if i % 2 == 0:
            ask.append(segment(conv[i]))
        else:
            response.append(segment(conv[i]))
'''
print(len(ask), len(response))

print(ask[:3])
print(response[:3])

1545400 1545400
['畹华吾侄', '咱们梅家从你爷爷起', '侍奉宫廷侍奉百姓']
['你接到这封信的时候', '就一直小心翼翼地唱戏', '从来不曾遭此大祸']

'''


def convert_seq2seq_files(questions, answers, TESTSET_SIZE=5000):
    # 创建文件
    train_enc = open('train.enc', 'w', encoding='utf-8')  # 问
    train_dec = open('train.dec', 'w', encoding='utf-8')  # 答
    test_enc = open('test.enc', 'w', encoding='utf-8')  # 问
    test_dec = open('test.dec', 'w', encoding='utf-8')  # 答

    # 选择8000数据作为测试数据
    test_index = random.sample([i for i in range(len(questions))], TESTSET_SIZE)

    for i in range(len(questions)):
        if i in test_index:
            test_enc.write(questions[i] + '\n')
            test_dec.write(answers[i] + '\n')
            train_enc.write(questions[i] + '\n')
            train_dec.write(answers[i] + '\n')
        else:
            train_enc.write(questions[i] + '\n')
            train_dec.write(answers[i] + '\n')
        if i % 1000 == 0:
            print(len(range(len(questions))), '处理进度:', i)

    train_enc.close()
    train_dec.close()
    test_enc.close()
    test_dec.close()


convert_seq2seq_files(ask, response)
