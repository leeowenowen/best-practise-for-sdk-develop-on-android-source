import os
import sys
import optparse
import re
import commands

def DoFindPub(path):
  result = set([])
  for root, dirs, files in os.walk( path ):
    for f in files:
      if not f.endswith('.class'):
        continue
      file_path = os.path.join(root, f)
      import FindPub
      pubs = FindPub.FindPub(file_path)
      if len(pubs) == 0:
        continue
      pub_list = pubs.split('####')
      for pub in pub_list:
        if len(pub) != 0:
          result.add(pub)
      #cmd = 'javap -c %s' % (file_path)
      #print cmd
      #status, content = commands.getstatusoutput(cmd)
      #regex = re.compile('\S{1,30}@\S{1,30}')
      #ret = regex.findall(content)
      #if len(ret) > 0:
      #  print ret
      #  return ret
      #result.extend(regex.findall(content))
  return result


def ParseOptions():
  parser = optparse.OptionParser()
  parser.add_option("-f", "--folder", help="class file folder")  
  options, args = parser.parse_args()
  return options 

def DoMain():
  options = ParseOptions()
  if not options:
    return
  result = DoFindPub(options.folder)
  print len(result)
  for item in result:
    print item
  

if __name__ == '__main__':
  DoMain()




