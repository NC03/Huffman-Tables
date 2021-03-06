all: build pack clean

export v=1.0.0
export msg=Auto Commit from Makefile

version:
	python3 version.py $(v)

build:
	javac source/*.java -d .

clean:
	rm *.class

pack:
	jar -cfe HuffmanTable.jar HuffmanTable *.class

commit:
	git add -A
	git commit -m "$(msg)"
	git push

documentation:
	javadoc source/*.java -d docs/

gui: build guijar clean

guijar:
	jar -cfe GUI.jar TextFileCompressor *.class
