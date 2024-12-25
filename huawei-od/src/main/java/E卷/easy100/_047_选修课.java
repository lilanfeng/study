package E卷.easy100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/14 16:23
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _047_选修课 {
    /**
     * 题目描述
     * 现有两门选修课，每门选修课都有一部分学生选修，每个学生都有选修课的成绩，需要你找出同时选修了两门选修课的学生，
     * 先按照班级进行划分，班级编号小的先输出，每个班级按照两门选修课成绩和的降序排序，成绩相同时按照学生的学号升序排序。
     * 输入描述
     * 第一行为第一门选修课学生的成绩，
     * 第二行为第二门选修课学生的成绩，每行数据中学生之间以英文分号分隔，每个学生的学号和成绩以英文逗号分隔，
     * 学生学号的格式为8位数字(2位院系编号+入学年份后2位+院系内部1位专业编号+所在班级3位学号)，
     * 学生成绩的取值范围为[0,100]之间的整数，
     *
     * 两门选修课选修学生数的取值范围为[1-2000]之间的整数。
     *
     * 输出描述
     * 同时选修了两门选修课的学生的学号，如果没有同时选修两门选修课的学生输出NULL，
     *
     * 否则，先按照班级划分，班级编号小的先输出，每个班级先输出班级编号(学号前五位)，然后另起一行输出这个班级同时选修两门选修课的学生学号，学号按照要求排序(按照两门选修课成绩和的降序，成绩和相同时按照学号升序)
     * ，学生之间以英文分号分隔。
     *
     * 示例1
     * 输入
     *
     * 01202021,75;01201033,95;01202008,80;01203006,90;01203088,100
     * 01202008,70;01203088,85;01202111,80;01202021,75;01201100,88
     * 输出
     *
     * 01202
     * 01202008;01202021
     * 01203
     * 01203088
     * 说明
     *
     * 同时选修了两门选修课的学生01202021、01202008、01203088，这三个学生两门选修课的成绩和分别为150、150、185，01202021、01202008属于01202
     * 班的学生，按照成绩和降序，成绩相同时按学号升序输出的结果为01202008;01202021,
     * 01203088属于01203班的学生，按照成绩和降序，成绩相同时按学号升序输出的结果为01203088，01202的班级编号小于01203的班级编号，需要先输出。
     *
     * 示例2
     * 输入
     *
     * 01201022,75;01202033,95;01202018,80;01203006,90;01202066,100
     * 01202008,70;01203102,85;01202111,80;01201021,75;01201100,88
     * 输出
     * NULL
     * 说明
     *
     * 没有同时选修了两门选修课的学生，输出NULL。
     *
     * 解题思路
     * 题目理解：
     * 题目要求我们处理两个选修课的学生成绩数据，并找出同时选修了两门选修课的学生。然后按照以下规则输出结果：
     *
     * 班级划分：学生学号的前五位代表班级编号，需要先按班级进行划分，班级编号小的先输出。
     * 排序规则：每个班级内部按照两门选修课成绩之和的降序排序。如果成绩和相同，则按照学号升序排序。
     * 输入格式：
     * 第一行是第一门选修课的学生成绩，每个学生的学号和成绩用英文逗号分隔，学生之间用英文分号分隔。
     * 第二行是第二门选修课的学生成绩，格式同上。
     * 学生的学号是8位数字，表示：
     * 前两位是院系编号，
     * 第3和第4位是入学年份，
     * 第5位是院系内部的专业编号，
     * 第6到第8位是班级学号。
     * 本题题目不难，就是拆解用例比较烦！
     */

    static class Student {
        String studentId; // 学生学号
        String classId; // 班级编号
        int score1 = -1; // 第一门选修课成绩
        int score2 = -1; // 第二门选修课成绩

        public int getSumScore() { // 计算两门选修课成绩和
            return this.score1 + this.score2;
        }
    }
    public static void main(String[] args) {
        String str1 = "01202021,75;01201033,95;01202008,80;01203006,90;01203088,100";
        String str2 = "01202008,70;01203088,85;01202111,80;01202021,75;01201100,88";
        String result = findStudents(str1, str2);
        System.out.println(result);
    }

    /**
     * 找出同时选修了两门选修课的学生
     * 并按照班级划分，班级编号小的先输出，每个班级按照两门选修课成绩和的降序排序，成绩相同时按照学生的学号升序排序
     * @param str1 选修课一 的学生成绩
     * @param str2 选修课二 的学生成绩
     * @return
     */
    public static String findStudents(String str1, String str2) {

        StringBuilder sb = new StringBuilder();
        HashMap<String, Student> studentMap = new HashMap<>();
        initStudent(str1,1,studentMap);
        initStudent(str2,2,studentMap);

        // 找出选修两门选修课的学生 按照classId分组
        HashMap<String, List<Student>> classMap = new HashMap<>();
        for (Student student: studentMap.values()) {
            if (student.score2 != -1 && student.score1 != -1) {
                String classId = student.classId;
                List<Student> studentList = classMap.getOrDefault(classId, new ArrayList<>());
                studentList.add(student);
                classMap.put(classId,studentList);
            }
        }
        if(classMap.size() <= 0){
            return "NULL";
        }
        classMap.keySet().stream().sorted(String::compareTo).forEach(classId->{
            List<Student> studentList = classMap.get(classId);
            sb.append(classId).append("\n");
            studentList.stream().sorted((s1,s2)->s1.getSumScore() != s2.getSumScore() ? s2.getSumScore() - s1.getSumScore() : s1.studentId.compareTo(s2.studentId))
                    .forEach(s->sb.append(s.studentId).append(";"));
            sb.append("\n");
        });
        return sb.toString();
    }

    /**
     * 初始化Student
     * @param str1
     */
    public static void initStudent(String str1,int classId,HashMap<String,Student> studentMap) {
        String[] strArr1 = str1.split(";");
        for (String str : strArr1) {
            String[] strArr = str.split(",");
            String studentId = strArr[0];
            Student student = null;
            if (studentMap.containsKey(studentId)) {
                student = studentMap.get(studentId);
            } else {
                student = new Student();
                student.classId = strArr[0].substring(0, 5);
                student.studentId = studentId;
            }
            if (classId == 1) {
                student.score1 = Integer.parseInt(strArr[1]);
            } else {
                student.score2 = Integer.parseInt(strArr[1]);
            }
            studentMap.put(studentId,student);
        }
    }


}
