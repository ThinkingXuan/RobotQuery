# 前一步生产的问答文件路径

train_encode_file = 'train.enc'
train_decode_file = 'train.dec'

test_encode_file = 'test.enc'
test_decode_file = 'test.dec'

print('开始创建词汇表')

# 特殊标记,用于填充标记对话

PAD = "__PAD__"
GO = "__GO__"

EOS = "__EOS__"  # 对话结束
UNK = "__UNK__"  # 标记未出现在词汇表中的字符

START_VOCABULART = [PAD, GO, EOS, UNK]

PAD_ID = 0
GO_ID = 1
EOS_ID = 2
UNK_ID = 3


# 参看tensorflow.models.rnn.translate.data_utils

# vocabulary_size = 5000


# 生成词汇表文件

def gen_vocabulary_file(input_file, output_file):
    vocabulary = {}

    with open(input_file, encoding='utf-8') as f:
        counter = 0
        for line in f:
            counter += 1
            tokens = [word for word in line.strip().split(' ')]
            for word in tokens:
                if word in vocabulary:
                    vocabulary[word] += 1
                else:
                    vocabulary[word] = 1

        vocabulary_list = START_VOCABULART + sorted(vocabulary, key=vocabulary.get, reverse=True)

        # 取签5000个常用汉字,应该差不多够用了(好多无用的字符，最好整理一下)


        print(input_file + " 词汇量大小:", len(vocabulary_list))
        with open(output_file, "w", encoding='utf-8') as ff:
            for word in vocabulary_list:
                ff.write(word + "\n")


gen_vocabulary_file(train_decode_file, 'train_decode_vocabulary')
gen_vocabulary_file(train_encode_file, 'train_encode_vocabulary')

train_encode_vocabulary_file = 'train_encode_vocabulary'
train_decode_vocabulary_file = 'train_decode_vocabulary'

print("对话转向量....")


# 把对话字符串转为向量的形式

def convert_to_vector(input_file, vocabulary_file, out_file):
    tmp_vocab = []
    with open(vocabulary_file, 'r', encoding='utf-8') as f:
        tmp_vocab.extend(f.readlines())
    tmp_vocab = [line.strip() for line in tmp_vocab]
    vocab = dict([(x, y) for (y, x) in enumerate(tmp_vocab)])

    # {'硕': 3142, 'v': 577, 'Ｉ': 4789, '\ue796': 4515, '拖': 1333, '疤': 2201 ...}
    print("vocab", vocab)
    output_f = open(out_file, 'w', encoding='utf-8')
    with open(input_file, 'r', encoding='utf-8') as f:
        for line in f:
            line_vec = []
            for words in line.strip().split(" "):
                print("words", words)
                line_vec.append(vocab.get(words, UNK_ID))
            output_f.write(" ".join([str(num) for num in line_vec]) + '\n')

    output_f.close()


convert_to_vector(train_encode_file, train_encode_vocabulary_file, 'train_encode.vec')
# convert_to_vector(train_decode_file, train_decode_vocabulary_file, 'train_decode.vec')

convert_to_vector(test_encode_file, train_encode_vocabulary_file, 'test_encode.vec')
# convert_to_vector(test_decode_file, train_decode_vocabulary_file, 'test_decode.vec')
