#!/usr/bin/python
#
# Listet alle @todo / TODO Hinweise aus Java-Quellcode auf einer Wiki-Seite auf
#
# @author Markus Tacker <m@tacker.org>
# @version $Id: make-todo.py 168 2011-01-10 09:46:35Z mtack001 $
#
import glob
import re
from io import open
import os
import sys
from xmlrpclib import Fault
from xmlrpclib import ServerProxy

# This is where the files are located
codepath = "../trunk/"
# This is the respective svn path
branch = "Code/trunk"
# Name of the wiki page to update
wikiPage = "Code/ToDo"

if len(sys.argv) < 3:
    sys.exit("Must provide username and password")
tracRPCURL = "https://{0}:{1}@scm.mi.hs-rm.de/trac/2010swt/2010swt03/login/rpc".format(sys.argv[1], sys.argv[2])
wikiSource = """= !ToDo =

Damit !ToDos aus unserem Quellcode hier auftauchen, schreiben wir im Java-Quellcode !ToDos als:

{{{
@todo TODO Hier sollte etwas gefixt werden.
}}}

Das geht auch inline:
{{{
TODO: Hier sollte etwas gefixt werden.
}}}

== Liste der !ToDos ==

"""

def javaFile(f):
    dirpath, dirnames, filenames = f
    svnPattern = re.compile('.+\.svn')
    if (svnPattern.match(dirpath) != None):
        return None
    javaFilePattern = re.compile('.+\.java$')
    javaFiles = []
    for file in filenames:
        if javaFilePattern.match(file):
            javaFiles.append(dirpath + "/" + file)
    return None if len(javaFiles) == 0 else javaFiles

todoPattern = re.compile('.*(@todo ){0,1}TODO:* (.+)')
unsupportedPattern = re.compile('.*throw new UnsupportedOperationException.*')
unsupported = ""
searchpath = os.path.realpath(os.path.dirname(sys.argv[0]) + "/" + codepath)
for javaFiles in map(javaFile, os.walk(searchpath)):
    if javaFiles == None:
        continue
    for javaFile in javaFiles:
        lineNum = 0
        for line in file(javaFile):
            lineNum += 1
            todoMatch = todoPattern.match(line)
            unsuppMatch = unsupportedPattern.match(line)

            sourceFile = javaFile
            sourceFile = sourceFile.replace(searchpath, "")
            sourceFile = sourceFile.replace("\\", "/")

            if todoMatch != None:
                wikiSource += "* [source:{0}/{1}#L{2} {3}:{4}][[BR]]{5}\n".format(branch, sourceFile, lineNum, os.path.basename(javaFile), lineNum, todoMatch.group(2))
            if unsuppMatch != None:
                unsupported += "* [source:{0}/{1}#L{2} {3}:{4}]\n".format(branch, sourceFile, lineNum, os.path.basename(javaFile), lineNum)

if len(unsupported) > 0:
	wikiSource += "\n=== !UnsupportedOperationException ===\n\n" + unsupported
# Update WikiPage
p = ServerProxy(tracRPCURL)
try:
    wikiSource = p.wiki.putPage(wikiPage, wikiSource, {'comment': 'Auto-Update'})
except Fault, err:
    if err.faultString != "'Page not modified' while executing 'wiki.putPage()'":
        raise err
