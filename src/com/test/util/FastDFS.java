package com.test.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFS {
    //配置文件本地路径
    public String conf_filepath = System.getProperty("user.dir")+"\\src\\fast_client.conf"; 

    /**
     * 
     * @param local_filename 上传文件本地路径
     * @param file_ext_name 上传文件后缀
     * @return fileIds 返回数组，第一个值为文件组，第二个值为文件路径
     */
    public String[] upload(String local_filename, String file_ext_name) {

        try { 
            ClientGlobal.init(conf_filepath);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            NameValuePair nvp [] = new NameValuePair[]{ 
                    new NameValuePair("age", "18"), 
                    new NameValuePair("sex", "male") 
            }; 
            String fileIds[] = storageClient.upload_file(local_filename, file_ext_name, nvp);

            System.out.println(fileIds.length); 
            System.out.println("组名：" + fileIds[0]); 
            System.out.println("路径：" + fileIds[1]);
            return fileIds;

        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (MyException e) { 
            e.printStackTrace(); 
        }
		return null; 
    }

    /**
     * 
     * @param file_buff 上传文件流
     * @param file_ext_name 上传文件后缀
     * @return fileIds 返回数组，第一个值为文件组，第二个值为文件路径
     */
    public String[] upload(byte[] file_buff, String file_ext_name) {

        try { 
            ClientGlobal.init(conf_filepath);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            NameValuePair nvp [] = new NameValuePair[]{ 
                    new NameValuePair("age", "18"), 
                    new NameValuePair("sex", "male") 
            }; 
            String fileIds[] = storageClient.upload_file(file_buff, file_ext_name, nvp);

            System.out.println(fileIds.length); 
            System.out.println("组名：" + fileIds[0]); 
            System.out.println("路径：" + fileIds[1]);
            return fileIds;

        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (MyException e) { 
            e.printStackTrace(); 
        }
		return null; 
    }

    /**
     * 
     * @param remotePath 远程文件路径
     * @param group 远程文件所属组
     * @param localPath 本地文件夹,比如D盘的temp文件夹写为 D:/temp
     * @return 返回操作是否成功
     */
    public boolean download(String remotePath, String group, String localPath) {
        try {

            ClientGlobal.init(conf_filepath);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            String temp[] = remotePath.split("/");
            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            byte[] b = storageClient.download_file(group, remotePath); 
            localPath = localPath +"\\"+ temp[temp.length-1];
            System.out.println("文件下载本地路径："+localPath); 
            IOUtils.write(b, new FileOutputStream(localPath));
            return true;
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
		return false; 
    }

    /**
     * 
     * @param remotePath 远程文件路径
     * @param group 远程文件所属组
     * @ 打印以下信息：[服务器地址, 文件大小, 创建时间, Crc32]
     */
    public String[] printFileInfo(String remotePath, String group){ 
        try { 
            ClientGlobal.init(conf_filepath);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            FileInfo fi = storageClient.get_file_info(group, remotePath);
            System.out.println(fi.getSourceIpAddr()); 
            System.out.println(fi.getFileSize()); 
            System.out.println(fi.getCreateTimestamp()); 
            System.out.println(fi.getCrc32()); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
		return null; 
    } 

    /**
     * 
     * @param remotePath 远程文件路径
     * @param group 远程文件所属组
     */
    public void printFileMate(String remotePath, String group){ 
        try { 
            ClientGlobal.init(conf_filepath);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, 
                    storageServer); 
            NameValuePair nvps [] = storageClient.get_metadata(group, remotePath); 
            for(NameValuePair nvp : nvps){ 
                System.out.println(nvp.getName() + ":" + nvp.getValue()); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 

    /**
     * 
     * @param remotePath 远程文件路径
     * @param group 远程文件所属组
     * @return 返回操作结果
     */
    public boolean delete(String remotePath, String group){ 
        try { 
            ClientGlobal.init(conf_filepath);

            TrackerClient tracker = new TrackerClient(); 
            TrackerServer trackerServer = tracker.getConnection(); 
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
            int i = storageClient.delete_file(group, remotePath); 
            System.out.println( i==0 ? "删除成功：" : "删除失败："+i); 
            return i==0;
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
		return false; 
    }
}