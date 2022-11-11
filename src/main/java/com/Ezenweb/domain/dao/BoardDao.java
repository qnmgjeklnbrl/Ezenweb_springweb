package com.Ezenweb.domain.dao;

import com.Ezenweb.domain.dto.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public BoardDao() {
        try{
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root",
                    "1234");
        }catch (Exception e){ System.out.println("연동실패");}
    }
    // 1. 게시물 등록 SQL
    public boolean setboard( BoardDto boardDto ){
        String sql = "insert into board( btitle , bcontent ) values(?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.executeUpdate(); return true;
        }catch (Exception e){ System.out.println(e); } return false;
    }

    public ArrayList<BoardDto> getboards(){
        ArrayList<BoardDto> arrayList = new ArrayList<>();
        String sql = "select * from board ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getInt(1))
                        .btitle(rs.getString(2))
                        .bcontent(rs.getString(3)).build();

                arrayList.add(boardDto);
            }
            return arrayList;
        }catch(Exception e){System.out.println(e);}return arrayList;
    }

}