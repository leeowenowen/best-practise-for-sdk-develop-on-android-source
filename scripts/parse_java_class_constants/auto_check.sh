apk_dir='apk_dir'
classes_dir='classes_dir'
########Step.1.
#1) fetch apk from remote url or load apk from local path
#2) decompile the apk
rm -rf $apk_dir
apktool d -o $apk_dir -s base.apk
cd $apk_dir
d2j-dex2jar.sh classes.dex
unzip classes-dex2jar.jar -d $classes_dir >/dev/null
python ../pub_check.py -f $classes_dir
cd ..
#3) check AndroidManifest.xml config
#python xml_check.py -f $apk_dir/AndroidManifest.xml -t Template.xml
#4) check facebook, union package existance
#5) check pub config
#python pub_check.py -f $classes_dir
#--a. find all pubs and list
#--b. check if the pub matches our configuration
#Users can do these steps in the future.
#6) clean work folder
#rm -rf $apk_dir
########Step.2. 
#1) user upload there sdk realted source code and brief description of their question
#2) auto send email to related project members
#3) check the source code and question manually



#Q&A
#6.0 security setting changed
