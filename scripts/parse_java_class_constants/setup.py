from distutils.core import setup, Extension
setup(name='FindPub', version='1.0',  \
      ext_modules=[Extension('FindPub', sources=['main.cpp'], extra_compile_args=['-std=c++11'])])
