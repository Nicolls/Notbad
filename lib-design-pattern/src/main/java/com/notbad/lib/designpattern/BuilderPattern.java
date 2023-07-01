package com.notbad.lib.designpattern;

/**
 * 建造者模式
 * 适用于内部构造类比较复杂，具有多个参数，多种构造方法等场景;使用一个内部Build类来完成对象的创建。
 * <p>
 * 场景：外部只需要提供创建此对象的必要信息，以及材料，就可以，而如何去创建不需要外部关心。
 * 比如要创建一幅画，只需要把画笔，颜料，板子，大小，
 * 类型传递builder即可，至于调配颜色，裁减纸张，成品装表，等则不用关心。传递完成调用创作即可返回作品。
 */
public class BuilderPattern {
    public static class Picture {
        private String pen;
        private String paper;
        private String colorPanel;

        private Picture() {
        }

        public static class Builder {
            private Picture picture;

            public Builder() {
                picture = new Picture();
            }

            public Builder setPen(String pen) {
                picture.pen = pen;
                return this;
            }

            public Builder setPaper(String paper) {
                picture.paper = paper;
                return this;
            }

            public Builder setColorPanel(String colorPanel) {
                picture.colorPanel = colorPanel;
                return this;
            }

            public Picture build() {
                return picture;
            }
        }

        public void show() {
            LogUtils.d("good draw");
        }
    }


    public static void main(String[] args) {
        new BuilderPattern().test();
    }

    public void test() {
        Picture picture = new Picture.Builder()
                .setPen("maobi")
                .setPaper("white")
                .setColorPanel("rgb")
                .build();
        picture.show();
    }
}
