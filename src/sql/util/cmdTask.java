package sql.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class cmdTask implements Runnable {
    private String command;

    public cmdTask (String command) {
        this.command = command;
    }

    @Override
    public void run() {
        Process process = null;
        int exitVal = 0;
        try {
            process = Runtime.getRuntime().exec(command);
            // Runtime.exec()创建的子进程公用父进程的流，不同平台上，父进程的stream buffer可能被打满导致子进程阻塞，从而永远无法返回。
            //针对这种情况，我们只需要将子进程的stream重定向出来即可。
            new RedirCmdStreamThread(process.getInputStream(), "INFO").start();
            new RedirCmdStreamThread(process.getErrorStream(), "ERR").start();

            exitVal = process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        if (exitVal != 0){
            throw new RuntimeException("cmd任务执行失败");
        }
    }

    class RedirCmdStreamThread extends Thread {
        InputStream is;
        String printType;

        RedirCmdStreamThread(InputStream is, String printType) {
            this.is = is;
            this.printType = printType;
        }

        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ( (line = br.readLine()) != null) {
                    System.out.println(printType + ">" + line);
                }

            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}