import os
import re
import sys

def process(path,file,version):
    directory = os.path.join(path,file)
    (root,ext) = os.path.splitext(directory)
    if ext == ".java":
        f = open(directory,"r")
        txt = f.read()
        f.close()
        match = re.search(r"@version ([\d\.]+)",txt)
        if match:
            txt = txt[:match.span()[0]] + "@version "+ version +txt[match.span()[1]:]
            f = open(directory,"w")
            f.write(txt)
            f.close()

if __name__ == "__main__":
    if len(sys.argv) > 1:
        version = sys.argv[1]
        for path, directory, files in os.walk("."):
            if path == "./source":
                for file in files:
                    process(path,file,version)