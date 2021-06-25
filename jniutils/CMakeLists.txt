# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

#查找当前目录下的所有源文件，并保存到 DIR_LIB_SRCS 变量
aux_source_directory(./src/main/cpp DIR_LIB_SRCS)
aux_source_directory(./src/main/cpp/libs DIR_FFMPEG_LIBS)
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
add_library(postproc
        SHARED
        IMPORTED)
add_library(swresample
        SHARED
        IMPORTED)
add_library(swscale
        SHARED
        IMPORTED)

set_target_properties(avcodec
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libavcodec.so)
set_target_properties(avdevice
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libavdevice.so)
set_target_properties(avfilter
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libavfilter.so)
set_target_properties(avformat
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libavformat.so)
set_target_properties(avutil
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libavutil.so)
set_target_properties(postproc
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libpostproc.so)
set_target_properties(swresample
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libswresample.so)
set_target_properties(swscale
        PROPERTIES
        IMPORTED_LOCATION
        ${DIR_FFMPEG_LIBS}/ffmpeg/libswscale.so)
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
