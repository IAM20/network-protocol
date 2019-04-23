cmake_minimum_required(VERSION 3.13)
project(ubp_client_cpp_ver)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++14")
set(SOURCE_FILES src/main.cpp)
# Find includes in corresponding build directories
set(CMAKE_INCLUDE_CURRENT_DIR ON)
# Instruct CMake to run moc automatically when needed
set(CMAKE_AUTOMOC ON)
# Create code from a list of Qt designer ui files
set(CMAKE_AUTOUIC ON)
set(CMAKE_PREFIX_PATH "/usr/local/Cellar/qt/5.12.2/lib/cmake")
set(GTEST_INCLUDE_DIR "~/git/googletest/googletest")

enable_testing()

if (APPLE)
    add_definitions(-DGTEST_USE_OWN_TR1_TUPLE)
    add_definitions(-D__GLIBCXX__)
endif (APPLE)

# Find the QtWidgets library
find_package(Qt5Widgets CONFIG REQUIRED)
find_package(Qt5Network CONFIG REQUIRED)
include_directories(${GTEST_INCLUDE_DIR}/include ${GTEST_INCLUDE_DIR} ${GTEST_INCLUDE_DIR}/make)

find_library(
        GTEST
        NAME gtest gtest_main
        HINTS ${GTEST_INCLUDE_DIR}/make
        NO_DEFAULT_PATH
)

add_executable(
        ubp_client_cpp_ver
        src/main.cpp
        src/RootUnit.cpp
        src/RootUnit.h
        src/view/PhotoViewer.cpp
        src/view/PhotoViewer.h
        src/view/PhotoThumbnail.cpp
        src/view/PhotoThumbnail.h
        src/model/Photo.cpp
        src/model/Photo.h
        src/model/PhotoGroup.cpp
        src/model/PhotoGroup.h
        src/api/PhotoManager.cpp
        src/api/PhotoManager.h
        test/QJsonParserTest.cpp
        test/ApiTest.cpp
        src/util/JsonParser.cpp
        src/util/JsonParser.h
        src/util/ImageDownloader.cpp
        src/util/ImageDownloader.h
        src/view/ViewerView.cpp
        src/view/ViewerView.h
        src/view/ThumbnailViewer.cpp
        src/view/ThumbnailViewer.h
        src/view/UploadView.cpp
        src/view/UploadView.h
        src/view/PhotoThumbnailForUpload.cpp
        src/view/PhotoThumbnailForUpload.h
        src/image/ImagePool.cpp
        src/image/ImagePool.h
        src/util/ThumbnailMaker.cpp src/util/ThumbnailMaker.h src/util/ImageFileManager.cpp src/util/ImageFileManager.h)

target_link_libraries(ubp_client_cpp_ver Qt5::Widgets Qt5::Network ${GTEST})
