for i in $(cat list.txt) ; do
svn --username pcampbell co $i
done 
