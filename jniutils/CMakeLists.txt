# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

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
        ${DIR_LIB_SRCS}/ffmpeg/libavcodec)
add_library(avdevice
        SHARED
        ${DIR_LIB_SRCS}/ffmpeg/libavdevice)
add_library(avfilter
        SHARED
        ${DIR_LIB_SRCS}/ffmpeg/libavfilter)
add_library(avformat
        SHARED
        ${DIR_LIB_SRCS}/ffmpeg/libavformat)
add_library(avutil
        SHARED
        ${DIR_LIB_SRCS}/ffmpeg/libavutil)
add_library(postproc
        SHARED
        ${DIR_LIB_SRCS}/ffmpeg/libpostproc)
add_library(swresample
        SHARED
        ${DIR_LIB_SRCS}/ffmpeg/libswresample)
add_library(swscale
        SHARED
        ${DIR_LIB_SRCS}/ffmpeg/libswscale)

target_link_libraries(
        native-lib
        avcodec
        avdevice
        avfilter
        avutil
        postproc
        swresample
        swscale
        ${log-lib}
)
