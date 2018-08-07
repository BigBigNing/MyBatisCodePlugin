package com.yn.code.ui;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.yn.code.generate.CodeGenerate;
import com.yn.code.model.ConfigModel;
import com.yn.code.util.CommonUtil;
import com.yn.code.util.MyException;
import com.yn.code.util.TableUtil;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ToolWindowUI {
    private JPanel toolWindowContent;

    private JTextField textFieldHost;
    private JTextField textFieldUserName;
    private JTextField textFieldPassword;
    private JTextField textFieldTableName;
    private JTextField textFieldAuthor;
    private JTextField textFieldProjectPath;

    private JComboBox comboBoxDatabase;
    private JComboBox comboBoxServicePath;
    private JComboBox comboBoxModelPath;
    private JComboBox comboBoxMapperPath;
    private JComboBox comboBoxControllerPath;

    private JButton goButton;
    private JButton modelPathSelectButton;
    private JButton mapperPathSelectButton;
    private JButton projectPathSelectButton;
    private JButton servicePathSelectButton;
    private JButton controllerPathSelectButton;

    private JCheckBox modelCheckBox;
    private JCheckBox mapperCheckBox;
    private JCheckBox controllerServiceCheckBox;


    private JLabel messageLabel;

    private String baseProjectPath;

    public JPanel getToolWindowContent(){
        return this.toolWindowContent;
    }

    private static final Logger LOGGER = Logger.getInstance(ToolWindowUI.class);

    public ToolWindowUI(Project project) {
        this.baseProjectPath = project.getBasePath();
        // 初始化checkbox
        modelCheckBox.setSelected(true);
        mapperCheckBox.setSelected(true);
        controllerServiceCheckBox.setSelected(true);

        PropertiesComponent propertiesComponentProject = PropertiesComponent.getInstance(project);
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();

        textFieldHost.setText(propertiesComponent.getValue("jdbcHost"));
        textFieldUserName.setText(propertiesComponent.getValue("jdbcUserName"));
        textFieldPassword.setText(propertiesComponent.getValue("jdbcPassword"));
        textFieldTableName.setText(propertiesComponent.getValue("tableName"));
        textFieldAuthor.setText(propertiesComponent.getValue("author"));
        comboBoxDatabase.addItem(propertiesComponent.getValue("jdbcDatabase"));
        comboBoxDatabase.setSelectedItem(propertiesComponent.getValue("jdbcDatabase"));
        textFieldProjectPath.setText(propertiesComponentProject.getValue("projectPath"));

        comboBoxControllerPath.addItem(propertiesComponentProject.getValue("controllerPath"));
        comboBoxControllerPath.setSelectedItem(propertiesComponentProject.getValue("controllerPath"));

        comboBoxServicePath.addItem(propertiesComponentProject.getValue("servicePath"));
        comboBoxServicePath.setSelectedItem(propertiesComponentProject.getValue("servicePath"));

        comboBoxModelPath.addItem(propertiesComponentProject.getValue("modelPath"));
        comboBoxModelPath.setSelectedItem(propertiesComponentProject.getValue("modelPath"));

        comboBoxMapperPath.addItem(propertiesComponentProject.getValue("mapperPath"));
        comboBoxMapperPath.setSelectedItem(propertiesComponentProject.getValue("mapperPath"));

        comboBoxDatabase.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                String jdbcHost = textFieldHost.getText();
                String jdbcUserName = textFieldUserName.getText();
                String jdbcPassword = textFieldPassword.getText();
                if(CommonUtil.isNullOrEmpty(jdbcHost)){
                    messageLabel.setText("Host:Port required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(jdbcUserName)){
                    messageLabel.setText("UserName required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(jdbcPassword)){
                    messageLabel.setText("Password required");
                    return;
                }
                try{
                    TableUtil tableUtil = new TableUtil(jdbcHost,jdbcUserName,jdbcPassword);
                    List<String> allDatabase = tableUtil.getAllDatabase();
                    comboBoxDatabase.removeAllItems();
                    allDatabase.forEach(databaseName -> {
                        comboBoxDatabase.addItem(databaseName);
                    });
                }catch (MyException myException){
                    messageLabel.setText(myException.getMeg());
                    LOGGER.info(myException);
                }
                messageLabel.setText("Plugin Message");
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        comboBoxControllerPath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxControllerPath, "controller");
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) { }
        });

        comboBoxServicePath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxServicePath, "service");
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) { }
        });

        comboBoxModelPath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxModelPath, "model");
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) { }
        });

        comboBoxMapperPath.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBoxClickAction(comboBoxMapperPath, "dao");
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) { }
        });

        projectPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(textFieldProjectPath,"select a project package path");

                comboBoxClickAction(comboBoxControllerPath, "controller");
                comboBoxClickAction(comboBoxServicePath, "service");
                comboBoxClickAction(comboBoxModelPath, "model");
                comboBoxClickAction(comboBoxMapperPath, "dao");
            }

        });

        controllerPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxControllerPath,"select a controller path");
            }
        });

        servicePathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxServicePath,"select a service path");
            }
        });

        modelPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxModelPath,"select a model path");
            }
        });

        mapperPathSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPathBtnAction(comboBoxMapperPath,"select a mapper path");
            }
        });


        goButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String jdbcHost = textFieldHost.getText();
                String jdbcUserName = textFieldUserName.getText();
                String jdbcPassword = textFieldPassword.getText();
                String jdbcDatabase = (String) comboBoxDatabase.getSelectedItem();
                String tableName = textFieldTableName.getText();
                String projectPath = textFieldProjectPath.getText();
                String controllerPath = (String) comboBoxControllerPath.getSelectedItem();
                String servicePath = (String) comboBoxServicePath.getSelectedItem();
                String modelPath = (String) comboBoxModelPath.getSelectedItem();
                String mapperPath = (String) comboBoxMapperPath.getSelectedItem();
                String author = textFieldAuthor.getText();
                if(CommonUtil.isNullOrEmpty(jdbcHost)){
                    messageLabel.setText("Host:Port required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(jdbcUserName)){
                    messageLabel.setText("UserName required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(jdbcPassword)){
                    messageLabel.setText("Password required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(jdbcDatabase)){
                    messageLabel.setText("Database required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(tableName)){
                    messageLabel.setText("TableName required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(projectPath)){
                    messageLabel.setText("ModelPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(controllerPath)){
                    messageLabel.setText("ModelPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(servicePath)){
                    messageLabel.setText("ModelPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(modelPath)){
                    messageLabel.setText("ModelPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(mapperPath)){
                    messageLabel.setText("MapperPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(author)){
                    textFieldAuthor.setText("default");
                    author = "default";
                }

                propertiesComponent.setValue("jdbcHost",jdbcHost);
                propertiesComponent.setValue("jdbcDatabase",jdbcDatabase);
                propertiesComponent.setValue("jdbcUserName",jdbcUserName);
                propertiesComponent.setValue("jdbcPassword",jdbcPassword);
                propertiesComponent.setValue("tableName",tableName);
                propertiesComponentProject.setValue("projectPath",projectPath);
                propertiesComponentProject.setValue("modelPath",modelPath);
                propertiesComponentProject.setValue("controllerPath",controllerPath);
                propertiesComponentProject.setValue("servicePath",servicePath);
                propertiesComponentProject.setValue("modelPath",modelPath);
                propertiesComponentProject.setValue("mapperPath",mapperPath);
                propertiesComponent.setValue("author",author);

                ConfigModel configModel = new ConfigModel();
                configModel.setJdbcHost(jdbcHost);
                configModel.setJdbcDatabase(jdbcDatabase);
                configModel.setJdbcUserName(jdbcUserName);
                configModel.setJdbcPassword(jdbcPassword);
                configModel.setControllerPath(CommonUtil.fomatPath(controllerPath));
                configModel.setServicePath(CommonUtil.fomatPath(servicePath));
                configModel.setModelPath(CommonUtil.fomatPath(modelPath));
                configModel.setMapperJavaPath(CommonUtil.fomatPath(mapperPath));
                configModel.setMapperXmlPath(CommonUtil.fomatPath(mapperPath));
                configModel.setAuthor(author);
                configModel.setGenerateModel(modelCheckBox.isSelected());
                configModel.setGenerateMapper(mapperCheckBox.isSelected());
                configModel.setGenerateControllerService(controllerServiceCheckBox.isSelected());

                List<String> success = new ArrayList<>();
                List<String> errors = new ArrayList<>();
                String[] names = tableName.split(",");
                for(String name : names){
                    configModel.setTableName(name);
                    try{
                        new CodeGenerate().generate(configModel);
                        success.add(name);
                    }catch (MyException myException){
                        errors.add(name + "(" + myException.getMeg() + ")");
                        LOGGER.info(myException);
                    }
                }
                if(errors.size() < 1){
                    messageLabel.setText("success!!!");
                } else {
                    String message = "";
                    for(String s : errors){
                        message += " " + s;
                    }
                    if(success.size() > 0){
                        message += " others is success";
                    }
                    messageLabel.setText(message);
                }

            }
        });
    }

    private void selectPathBtnAction(JComboBox comboBox, String message){
        String defaultFilePath;
        if(!CommonUtil.isNullOrEmpty(comboBox.getSelectedItem())){
            defaultFilePath = (String) comboBox.getSelectedItem();
        }else if(!CommonUtil.isNullOrEmpty(textFieldProjectPath.getText())){
            defaultFilePath = textFieldProjectPath.getText();
        }else {
            defaultFilePath = baseProjectPath;
        }
        JFileChooser jFileChooser = new JFileChooser(new File(defaultFilePath));
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setDialogTitle(message);
        jFileChooser.showDialog(new JLabel(),"select");
        File file = jFileChooser.getSelectedFile();
        if(file == null){
            return;
        }
        comboBox.addItem(file.getAbsolutePath());
        comboBox.setSelectedItem(file.getAbsolutePath());
        messageLabel.setText("Plugin Message");
    }

    private void selectPathBtnAction(JTextField textField, String message){
        String defaultFilePath;
        if(!CommonUtil.isNullOrEmpty(textField.getText())){
            defaultFilePath = textField.getText();
        }else if(!CommonUtil.isNullOrEmpty(textFieldProjectPath.getText())){
            defaultFilePath = textFieldProjectPath.getText();
        }else {
            defaultFilePath = baseProjectPath;
        }
        JFileChooser jFileChooser = new JFileChooser(new File(defaultFilePath));
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setDialogTitle(message);
        jFileChooser.showDialog(new JLabel(),"select");
        File file = jFileChooser.getSelectedFile();
        if(file == null){
            return;
        }
        textField.setText(file.getAbsolutePath());
        messageLabel.setText("Plugin Message");
    }

    private void comboBoxClickAction(JComboBox comboBox, String key){
        String projectPath = textFieldProjectPath.getText();
        if(CommonUtil.isNullOrEmpty(projectPath)){
            messageLabel.setText("ProjectPath required");
            return;
        }
        List<File> directoryByProjectPath = CommonUtil.searchDirectory(projectPath,key);
        comboBox.removeAllItems();
        if(directoryByProjectPath != null && directoryByProjectPath.size() > 0){
            directoryByProjectPath.forEach(file -> {
                comboBox.addItem(file.getAbsolutePath());
            });
            comboBox.setSelectedItem(directoryByProjectPath.get(0).getAbsolutePath());
        }else {
            messageLabel.setText("not found "+ key + " path");
        }
    }
}
