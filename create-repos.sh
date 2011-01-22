#!/bin/bash
rm -fr repos
svnadmin create repos
PWD=`pwd`
svn import 900-release file://$PWD/repos/trunk  -m"- First import."
svn mkdir --parents file://$PWD/repos/tags -m"- Tags folders."
svn co file://$PWD/repos/trunk wc-900-release
