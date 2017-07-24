package bean;

/**
 * Created by j on 2017/7/22.
 */

public class People {
        public int ID = -1;
        public String Name;
        public int Num;
        public float Chin;
        public float Math;
        public float Eng;

        @Override
        public String toString(){
            String result = "";
            result += "ID：" + this.ID + ",";
            result += "姓名：" + this.Name + ",";
            result += "学号" + this.Num + ", ";
            result += "语文" + this.Chin + ",";
            result += "数学" + this.Math + ",";
            result += "英语" + this.Eng + ".";
            return result;
        }
    }


