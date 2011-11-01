# -*- coding: utf-8 -*-
#
# Erzeugt ein großes Dokument aus vielen einzelenen Java-Doc-Dateien
#
# @autor: Markus Tacker <m@tacker.org>
# @version: $Id: make-html.py 87 2010-12-19 16:20:46Z mtack001 $
#

import re
from io import open
import sys
import os

class JavaDocFile(object):
    "Parser für ein JavaDoc-File"
    jfile = None
    useline = False
    body = ""
    docDir = None
    className = None
    
    def __init__(self, jfile, docDir):
        self.jfile = file(jfile)
        self.docDir = docDir
        self.package = package
        self.className = self.modelname_from_link(jfile.replace(docDir, ''))
        self.parse()

    def parse(self):
        comment_pattern = re.compile("^<!-- =+([^=]+)=+ -->")
        for line in self.jfile:
            comment_match = comment_pattern.match(line)
            if (comment_match != None):
                if comment_match.group(1).strip() == "START OF CLASS DATA":
                    self.useline = True
                    self.handle_line('<A NAME="' + self.className + '" /></a>' + "\n")
                if comment_match.group(1).strip() == "END OF CLASS DATA":
                    self.handle_line("<hr />\n")
                    self.useline = False
            self.handle_line(line)

    def handle_line(self, line):
        if self.useline != True: return
        self.body = self.body + line

    def get_body(self):
        "Gibt den geparsten Body der Datei zurück"
        # Image links
        body = re.sub(r'SRC="(\.\.\/)+', 'SRC="' + self.docDir, self.body)
        # Model links
        class_pattern = re.compile('<A HREF="([^"]+)" title="class in ([^"]+)"')
        for match in re.findall(class_pattern, body):
            body = body.replace('<A HREF="{0}" title="class in {1}"'.format(match[0], match[1]), '<A HREF="#{0}" title="class in {1}"'.format(self.modelname_from_link(match[0]), match[1]))
        # Method links
        method_name_pattern = re.compile('<A NAME="([a-zA-Z]+(\([^\)]*\))*)"')
        for match in re.findall(method_name_pattern, body):
            body = body.replace('<A NAME="{0}"'.format(match[0]), '<A NAME="{0}"'.format(self.className + '--' + match[0]))

        method_link_pattern = re.compile('<A HREF="([\/\.]+)(([a-z]+\/)+[^\.]+)\.html#([a-zA-Z]+(\([^\)]*\))*)"')
        for match in re.findall(method_link_pattern, body):
            s = '<A HREF="{0}{1}.html#{2}"'.format(match[0], match[1], match[3])
            r = '<A HREF="#{0}--{1}"'.format(self.modelname_from_link('/' + match[1] + '.html'), match[3])
            body = body.replace(s, r)

        # done
        return body

    def modelname_from_link(self, link):
        return re.sub(r'^\/', '', link.split('.')[-2]).replace('/', '.')
        
    

docDir = "../java/doc/"


fp = open('javadoc.html', 'w+')
fp.write(unicode('<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html><head><TITLE>Verkehrsplaner</TITLE><LINK REL="stylesheet" TYPE="text/css" HREF="' + docDir + 'stylesheet.css" TITLE="Style"></head><BODY BGCOLOR="white">' + "\n"));

packages = {}

class_pattern = re.compile('^<A HREF="([^"]+)" title="class in ([^"]+)"')
for line in file(docDir + "allclasses-noframe.html"):
    class_match = class_pattern.match(line)
    if (class_match != None):
        packageGroup = packages.get(class_match.group(2), [])
        packageGroup.append(class_match.group(1))
        packages[class_match.group(2)] = packageGroup;

# Datei zusammenpacken
for package in packages:
    for classFile in packages[package]:
        jfile = JavaDocFile(docDir + classFile, docDir)
        fp.write(unicode(jfile.get_body(), 'utf-8'))

fp.write(unicode("</body></html>\n"));
fp.close()
