# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
set(distribution_DIR ./src/libs)
#查找当前目录下的所有源文件，并保存到 DIR_LIB_SRCS 变量
aux_source_directory(./src/main/cpp DIR_LIB_SRCS)
include_directories(./src/main/cpp)

find_library(
        log-lib
        log)

add_library(
        native-lib
        SHARED
        ${DIR_LIB_SRCS}
)
add_library(avcodec
        SHARED
        IMPORTED)
add_library(avdevice
        SHARED
        IMPORTED)
add_library(avfilter
        SHARED
        IMPORTED)
add_library(avformat
        SHARED
        IMPORTED)
add_library(avutil
        SHARED
        IMPORTED)
add_library(swresample
        SHARED
        IMPORTED)
add_library(swscale
        SHARED
        IMPORTED)

set_target_properties(avcodec
        PROPERTIES IMPORTED_LOCATION
       ./src/libs/ffmpeg/armeabi-v7a/libavcodec.so)
set_target_properties(avdevice
        PROPERTIES IMPORTED_LOCATION
       ./src/libs/ffmpeg/armeabi-v7a/libavdevice.so)
set_target_properties(avfilter
        PROPERTIES IMPORTED_LOCATION
       ./src/libs/ffmpeg/armeabi-v7a/libavfilter.so)
set_target_properties(avformat
        PROPERTIES IMPORTED_LOCATION
       ./src/libs/ffmpeg/armeabi-v7a/libavformat.so)
set_target_properties(avutil
        PROPERTIES IMPORTED_LOCATION
       ./src/libs/ffmpeg/armeabi-v7a/libavutil.so)
set_target_properties(swresample
        PROPERTIES IMPORTED_LOCATION
       ./src/libs/ffmpeg/armeabi-v7a/libswresample.so)
set_target_properties(swscale
        PROPERTIES IMPORTED_LOCATION
       ./src/libs/ffmpeg/armeabi-v7a/libswscale.so)
target_link_libraries(
        native-lib
        avcodec
        avdevice
        avfilter
        avutil
        swresample
        swscale
        ${log-lib}
)
