# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.10.1)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
set(distribution_DIR ${CMAKE_SOURCE_DIR}/src/libs)
set(CURRENT_DIR ${CMAKE_SOURCE_DIR})
message("CURRENT_DIR:" ${CMAKE_SOURCE_DIR})

#查找当前目录下的所有源文件，并保存到 DIR_LIB_SRCS 变量
aux_source_directory(./src/main/cpp DIR_LIB_SRCS)
include_directories(./src/main/cpp)
include_directories(./src/main/cpp/include)

find_library(
        log-lib
        log)

add_library(
        native-lib
        SHARED
        ${DIR_LIB_SRCS}
)

target_link_libraries(
        native-lib
        ${log-lib}
)
