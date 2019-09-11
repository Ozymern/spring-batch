package com.ozymern.springbatch.writer;

import com.ozymern.springbatch.entities.User;
import com.ozymern.springbatch.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserWriter implements ItemWriter<List<User>> {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserWriter.class);

    @Override
    public void write(List<? extends List<User>> list) throws Exception {


        if (list.get(0).isEmpty())
            LOGGER.info("user is empty, data writer .txt canceled");

        else
            this.generateTxtFile("C:/output", "TRN6201" + DateUtil.formatCollectionDate(), list.get(0));

    }



    private  void generateTxtFile(
        String folderPath,
        String txtfileName,
        List<User> users) {

        LOGGER.info("generating file .TXT");

        try {

            String ruta = folderPath + File.separator + txtfileName + ".TXT";

            File file = new File(ruta);

            if (!file.exists()) {

                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0 ; i < users.size() ; i++) {

                User user = users.get(i);

                String line =
                        user.getName()+","+
                        user.getLastName()+","+
                        user.getEmail()+","+
                        user.getSex()+","+
                        user.getAge();

                bufferedWriter.write(line);

                if (i != users.size() - 1)
                    bufferedWriter.newLine();
            }

            bufferedWriter.close();

            LOGGER.info("generating .TXT succesful");

        } catch (IOException ioException) {

            LOGGER.error(ioException.getMessage());
        }
    }
}
