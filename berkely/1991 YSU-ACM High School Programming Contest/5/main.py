import re, string, os, sys

pattern = r'([\s\S]*)(\(.*\))([\s\S]*)'
def main():
    path = os.path.join(sys.path[0], 'input')
    mapping = str.maketrans(string.ascii_uppercase, string.ascii_uppercase[::-1])
    print(string.ascii_uppercase)
    print(string.ascii_uppercase[::-1])
    data = open(path, 'r').read()
    matches = re.findall(pattern, data)[0]
    result = ''
    for match in matches:
        result += match if match.startswith('(') else match.translate(mapping)
    print(result)