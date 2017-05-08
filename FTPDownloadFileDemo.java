import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPDownloadFileDemo 
{
    public static void main(String[] args) 
    {
        String server = "192.168.30.15";
        int port = 21;
        String user = "ftpuser";
        String pass = "ftp";
 
        FTPClient ftpClient = new FTPClient();
        try 
        {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "/lehrer/vertretungsplan.pdf";
            File downloadFile1 = new File("H:/Q_/Q_039/temp/vertretungsplan.pdf");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) 
            {
                System.out.println("File #1 has been downloaded successfully.");
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } 
        finally 
        {
            try 
            {
                if (ftpClient.isConnected()) 
                {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
    }
}