package blog.csdn.net;

public class ClassA {

    private static class InnerClassA {

        private String name;

        private InnerClassA(String name) {
            this.name = name;
        }

        public String run() {
            return name;
        }
    }

}
