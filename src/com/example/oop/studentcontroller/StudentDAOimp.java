package com.example.oop.studentcontroller;

import java.sql.*;
import com.example.oop.model.Student;
import com.example.oop.studentdb.StudentDb;
import com.google.zxing.LuminanceSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class StudentDAOimp implements StudentDAO {
    
    
    
    
    
    public class BufferedImageLuminanceSource extends LuminanceSource {

    private final BufferedImage image;

    public BufferedImageLuminanceSource(BufferedImage image) {
        super(image.getWidth(), image.getHeight());
        this.image = image;
    }

    @Override
    public byte[] getRow(int y, byte[] row) {
        // Get the luminance data for the specified row
        int width = getWidth();
        if (row == null || row.length < width) {
            row = new byte[width];
        }

        for (int x = 0; x < width; x++) {
            row[x] = (byte) getLuminance(x, y);
        }

        return row;
    }

    @Override
    public byte[] getMatrix() {
        // Flatten the entire luminance data into a single array
        int width = getWidth();
        int height = getHeight();
        byte[] matrix = new byte[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matrix[y * width + x] = (byte) getLuminance(x, y);
            }
        }

        return matrix;
    }

    private int getLuminance(int x, int y) {
        // Get the luminance value for the specified pixel
        int pixel = image.getRGB(x, y);
        int red = (pixel >> 16) & 0xFF;
        int green = (pixel >> 8) & 0xFF;
        int blue = pixel & 0xFF;
        return (red + green + blue) / 3;
    }
}

@Override
public void save(Student student) {
    try {
        Connection con = StudentDb.getConnection();
        String sql = "INSERT INTO studentprofile(StudentID, Name, Age, YearAndSection, Image) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, student.getStudentID());
        ps.setString(2, student.getName());
        ps.setString(3, student.getAge());
        ps.setString(4, student.getYearAndSection());

        // Check if the image data is null, and set an empty byte array if null
        byte[] imageData = student.getImage();
        if (imageData == null) {
            imageData = new byte[0];
        }

        ps.setBlob(5, new ByteArrayInputStream(imageData));
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Student information saved successfully!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}


    @Override
    public void update(Student student) {
        
     try {
        Connection con = StudentDb.getConnection();
        String sql = "UPDATE studentprofile SET Name=?, Age=?, YearAndSection=? WHERE StudentID=?";

        PreparedStatement ps = con.prepareStatement(sql);

          ps.setInt(1, student.getStudentID());
        ps.setString(2, student.getName());
        ps.setString(3, student.getAge());
        ps.setString(4, student.getYearAndSection());
      

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Update successful!");
        } else {
            JOptionPane.showMessageDialog(null, "No record found for the given StudentID.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    @Override
    public void AddImage(Student student) {
       
    }

  @Override
public Student get(int id) {
    Student st = new Student();
    try {
        Connection con = StudentDb.getConnection();
        String sql = "SELECT * FROM studentprofile WHERE StudentID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            st.setStudentID(rs.getInt("StudentID"));
            st.setName(rs.getString("Name"));
            st.setAge(rs.getString("Age"));
            st.setYearAndSection(rs.getString("YearAndSection"));
            // Retrieve image data from the result set
            byte[] imageData = rs.getBytes("Image");
            st.setImage(imageData); // Assuming your Student class has a setImage method
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    return st;
}


    @Override
    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        try {
            Connection con = StudentDb.getConnection();
            String sql = "SELECT * FROM studentprofile";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student st = new Student();
                 st.setStudentID(rs.getInt("StudentID"));
                st.setName(rs.getString("Name"));
                st.setAge(rs.getString("Age"));
                st.setYearAndSection(rs.getString("YearAndSection"));
                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return list;
    }

    public Student getStudentByID(int studentID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Student getStudentB(int studentID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Student save(int studentID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
