package com.haders.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class FileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    public static TestPathBuild pathBuild() {
        return new TestPathBuild();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }

    /**
     * build to test file path
     **/
    public static class TestPathBuild {
        private TestPathBuild() {
            subPath = new ArrayList<>();
        }

        private final List<String> subPath;

        public TestPathBuild sub(String dirOrFile) {
            subPath.add(dirOrFile);
            return this;
        }

        public String getPath() {
            if (CollectionUtils.isEmpty(subPath)) {
                return FileUtil.class.getResource("/").getPath();
            }
            if (subPath.size() == 1) {
                return FileUtil.class.getResource("/").getPath() + subPath.get(0);
            }
            StringBuilder path = new StringBuilder(FileUtil.class.getResource("/").getPath());
            path.append(subPath.get(0));
            for (int i = 1; i < subPath.size(); i++) {
                path.append(File.separator).append(subPath.get(i));
            }
            return path.toString();
        }

    }

}
