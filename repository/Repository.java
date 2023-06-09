package repository;

import Connection.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//디비 접근하는 친구
public class Repository {
    public static void preInsert(String movieName,String dateOfPreview) {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into preview(movie_name, date_of_preview)\n" +
                "values (?, ?)";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, movieName);
            psmt.setString(2, dateOfPreview);
            if (psmt.executeUpdate() == 0) {
                System.out.println("insertPreview err");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }

    public static void cinInsert(String cinemaName,String address,String numChair){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into cinema(cinema_name, address,capacity_chair)\n" +
                "values (?, ?,?)";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, cinemaName);
            psmt.setString(2, address);
            psmt.setString(3, numChair);

            if (psmt.executeUpdate() == 0) {
                System.out.println("insertPreview err");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String select_sql = "select id from cinema " +     // sql 구문에서 where 앞에 띄어쓰기를 해줘야됨
                "where cinema_name=? and address=? and capacity_chair=?";
        Integer cinemaId = null;
        try {
            PreparedStatement psmt = conn.prepareStatement(select_sql);
            psmt.setString(1,cinemaName);
            psmt.setString(2,address);
            psmt.setString(3,numChair);
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()){
                cinemaId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql3 = "insert into chair(x, y,cinema_id)\n" +
                "values (?, ?, ?)";
        int x;
        int y;
        for(int i=0;i<Integer.parseInt(numChair);i++) {
            y=(int)(i/5)+1;
            x=(i%5)+1;
            try {
                PreparedStatement psmt = conn.prepareStatement(sql3);
                psmt.setInt(1, x);
                psmt.setInt(2, y);
                psmt.setInt(3, cinemaId);
                if (psmt.executeUpdate() == 0) {
                    System.out.println("insertPreview err");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }

    public static void printCinema() {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from cinema ";
        Integer id=null;
        String cinemaName=null;
        String address=null;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet res = psmt.executeQuery();
            System.out.println("id\tcinemaName\taddress");
            while (res.next()){
                id=res.getInt("id");
                cinemaName=res.getString("cinema_name");
                address=res.getString("address");
                System.out.printf("%-4d%-12s%s\n",id,cinemaName,address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }


    public static void printPreview() {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from preview";
        Integer id=null;
        String movieName=null;
        String dateOfPreview=null;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet res = psmt.executeQuery();
            System.out.println("id\tmovieName\tdateOfPreview");
            while (res.next()){
                id=res.getInt("id");
                movieName=res.getString("movie_name");
                dateOfPreview=res.getString("date_of_preview");
                System.out.printf("%-4d%-13s%s\n",id,movieName,dateOfPreview);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }

    public static void printPerson() {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from person";
        Integer id=null;
        String name=null;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet res = psmt.executeQuery();
            System.out.println("id\tname");
            while (res.next()){
                id=res.getInt("id");
                name=res.getString("name");
                System.out.printf("%-4d%s\n",id,name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }

    public static void insertCombinePreview(String previewId,String cinemaId){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into preview_cinema(preview_id, cinema_id)\n" +
                "values (?, ?)";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, previewId);
            psmt.setString(2, cinemaId);
            if (psmt.executeUpdate() == 0) {
                System.out.println("insertPreview err");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }

    public static void relatedCinemaPrint(String previewId){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from (select * from cinema,preview_cinema where cinema.id=preview_cinema.cinema_id)as t where preview_id=?";
        Integer id=null;
        String cinemaName=null;
        String address=null;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,previewId);
            ResultSet res = psmt.executeQuery();
            System.out.println("id\tcinemaName\taddress");
            while (res.next()){
                id=res.getInt("id");
                cinemaName=res.getString("cinema_name");
                address=res.getString("address");
                System.out.printf("%-4d%-14s%s\n",id,cinemaName,address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }
    public static void printChair(String cinemaId){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from chair "+
                "where cinema_id=?";

        Integer id=null;
        Integer x=null;
        Integer y=null;
        String status=null;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,cinemaId);
            ResultSet res = psmt.executeQuery();
            System.out.println("id\tx\ty\tstatus");
            while (res.next()){
                id=res.getInt("id");
                x=res.getInt("x");
                y=res.getInt("y");
                status=res.getString("status");
                System.out.printf("%-4d%-5d%-5d%s\n",id,x,y,status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }

    public static void changeStatusAndPersonInsert(String chairId,String name,String phoneNum){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "update chair set status=\'x\' where id=?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, chairId);
            if (psmt.executeUpdate() == 0) {
                System.out.println("updateChair err");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql5 = "insert into person(chair_id, name,phone_num)\n" +
                "values (?, ?, ?)";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql5);
            psmt.setString(1, chairId);
            psmt.setString(2, name);
            psmt.setString(3, phoneNum);
            if (psmt.executeUpdate() == 0) {
                System.out.println("insertPreview err");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection close fail");
        }
    }
}

