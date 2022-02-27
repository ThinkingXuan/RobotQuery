import jieba


def segment(input_str):
    input_str = input_str.strip()
    output_str = jieba.cut(input_str)
    segments = ""
    for str in output_str:
        segments = segments + " " + str
    return segments

segment("你个狗")
