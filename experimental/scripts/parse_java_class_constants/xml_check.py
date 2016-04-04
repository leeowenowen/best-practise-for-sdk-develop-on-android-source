import os
import sys
import optparse

XMLNS_PREFIX = '{http://schemas.android.com/apk/res/android}'
NAME = XMLNS_PREFIX + 'name'

def Log(content):
  import platform
  if content:
    if sys.version_info < (3, 0) and platform.system() == 'Windows':
      print (content.decode('utf-8').encode('gbk'))
    else:
      print (content)

def IsNodeTagNameMatch(node, node_sub):
  if not node.tag == node_sub.tag:
    return (False, ['tag is not equal'])
  name = node.get(NAME)
  name_sub = node_sub.get(NAME)
  if name is None and name_sub is None:
    return (True, []) 
  elif name is not None and name_sub is not None and name == name_sub:
    return (True, [])
  return (False, ['node not match[tag:%s][tag_name:%s][tag_sub_name:%s]' % (node.tag, name, name_sub)])
   

def IsNodeMatch(node, node_sub):
  (result, msg) = IsNodeTagNameMatch(node, node_sub)
  if not result:
    return (result, msg)
  for key in node_sub.keys():
    if key not in node.keys():
      msg.append('Node Missing:[key:%s] not find!' % key)
      return (False, msg)
    if node.get(key) != node_sub.get(key):
      msg.append('Node, NotMatch!, should be [%s] but find [%s]' % (node_sub.get(key), node.get(key)))
      return (False, msg)
  return (True, []) 

def IsNodeInList(node_list, node_sub):
  msg = []
  for node in node_list:
    (result, msg) = IsTreeMatch(node, node_sub)
    if result:
      return (result, msg)
  msg.append('[tag:%s][name:%s]' % (node_sub.tag, node_sub.get(NAME)))
  return (False, msg)

def IsChildrenMatch(root, root_sub):
  children_sub = list(root_sub)
  for child_sub in children_sub:
    children = []
    for child in list(root):
      (result,msg) = IsNodeTagNameMatch(child, child_sub) 
      if result:
        children.append(child)
    (result, msg) = IsNodeInList(children,child_sub)
    if not result:
      return (result, msg)
  return (True, []) 

def IsTreeMatch(root, root_sub):
  (result, msg) = IsNodeMatch(root, root_sub) 
  if not result:
    return (result,msg)
  
  (result, msg) = IsChildrenMatch(root, root_sub)
  if not result:
    return (result, msg)
  return (True, '') 

def ParseOptions():
  parser = optparse.OptionParser()
  parser.add_option("-f", "--android_manifest", help="AndroidManifest.xml path")  
  parser.add_option("-t", "--android_manifest_template", help="AndroidManifest Template xml")  
  options, args = parser.parse_args()
  if options.android_manifest is None:
    Log('AndroidManifest.xml path can not be null!')
    return None
  if options.android_manifest_template is None:
    Log('AndroidManifestTemplate.xml path can not be null!')
    return None
  return options 

def Main():
  options = ParseOptions()
  if not options:
    return
  import xml.etree.ElementTree as ET
  root = ET.parse(options.android_manifest).getroot()
  root_sub = ET.parse(options.android_manifest_template).getroot()
  (result, msg) = IsTreeMatch(root, root_sub)
  if not result:
    count = 0
    for frame in msg:
      count = count + 1
      Log('error frame%d:%s' %(count, frame)) 
  else:
    Log('Match') 
  return result

 
if __name__ == '__main__':
  Main()
