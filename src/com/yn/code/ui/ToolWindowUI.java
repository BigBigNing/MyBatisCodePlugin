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
import java.util.List;

public class ToolWindowUI {
    private JPanel toolWindowContent;
    private JTextField textFieldHost;
    private JTextField textFieldUserName;
    private JTextField textFieldPassword;
    private JTextField textFieldTableName;
    private JTextField textFieldModelPath;
    private JTextField textFieldMapperJavaPath;
    private JTextField textFieldMapperXmlPath;
    private JTextField textFieldAuthor;
    private JComboBox comboBoxDatabase;
    private JButton goButton;
    private JLabel messageLabel;
    private JButton modelButton;
    private JButton mapperJavaButton;
    private JButton mapperXmlButton;
    private JCheckBox modelCheckBox;
    private JCheckBox mapperCheckBox;
    private JLabel selectLabel;
    private String projectPath;
    private Project project;

    public JPanel getToolWindowContent(){
        return this.toolWindowContent;
    }

    private static final Logger LOGGER = Logger.getInstance(ToolWindowUI.class);

    public ToolWindowUI(Project project) {
        this.project = project;
        this.projectPath = project.getBasePath();
        // 初始化checkbox
        modelCheckBox.setSelected(true);
        mapperCheckBox.setSelected(true);

        PropertiesComponent propertiesComponentProject = PropertiesComponent.getInstance(project);
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        textFieldHost.setText(propertiesComponent.getValue("jdbcHost"));
        textFieldUserName.setText(propertiesComponent.getValue("jdbcUserName"));
        textFieldPassword.setText(propertiesComponent.getValue("jdbcPassword"));
        textFieldTableName.setText(propertiesComponent.getValue("tableName"));
        textFieldModelPath.setText(propertiesComponentProject.getValue("modelPath"));
        textFieldMapperJavaPath.setText(propertiesComponentProject.getValue("mapperJavaPath"));
        textFieldMapperXmlPath.setText(propertiesComponentProject.getValue("mapperXmlPath"));
        textFieldAuthor.setText(propertiesComponent.getValue("author"));
        comboBoxDatabase.addItem(propertiesComponent.getValue("jdbcDatabase"));
        comboBoxDatabase.setSelectedItem(propertiesComponent.getValue("jdbcDatabase"));

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
                TableUtil tableUtil = new TableUtil(jdbcHost,jdbcUserName,jdbcPassword);
                List<String> allDatabase = tableUtil.getAllDatabase();
                comboBoxDatabase.removeAllItems();
                allDatabase.forEach(databaseName -> {
                    comboBoxDatabase.addItem(databaseName);
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        modelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String defaultFilePath;
                if(!CommonUtil.isNullOrEmpty(textFieldModelPath.getText())){
                    defaultFilePath = textFieldModelPath.getText();
                }else if(!CommonUtil.isNullOrEmpty(textFieldMapperJavaPath.getText())){
                    defaultFilePath = textFieldMapperJavaPath.getText();
                }else if(!CommonUtil.isNullOrEmpty(textFieldMapperXmlPath.getText())){
                    defaultFilePath = textFieldMapperXmlPath.getText();
                }else {
                    defaultFilePath = projectPath;
                }
                JFileChooser jFileChooser = new JFileChooser(new File(defaultFilePath));
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.setDialogTitle("select model path");
                jFileChooser.showDialog(new JLabel(),"select");
                File file = jFileChooser.getSelectedFile();
                if(file == null){
                    return;
                }
                textFieldModelPath.setText(file.getAbsolutePath());
                if(CommonUtil.isNullOrEmpty(textFieldMapperJavaPath.getText())){
                    textFieldMapperJavaPath.setText(file.getAbsolutePath());
                }
                if(CommonUtil.isNullOrEmpty(textFieldMapperXmlPath.getText())){
                    textFieldMapperXmlPath.setText(file.getAbsolutePath());
                }
            }
        });
        mapperJavaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String defaultFilePath;
                if(!CommonUtil.isNullOrEmpty(textFieldMapperJavaPath.getText())){
                    defaultFilePath = textFieldMapperJavaPath.getText();
                }else if(!CommonUtil.isNullOrEmpty(textFieldMapperXmlPath.getText())){
                    defaultFilePath = textFieldMapperXmlPath.getText();
                }else if(!CommonUtil.isNullOrEmpty(textFieldModelPath.getText())){
                    defaultFilePath = textFieldModelPath.getText();
                }else {
                    defaultFilePath = projectPath;
                }
                JFileChooser jFileChooser = new JFileChooser(new File(defaultFilePath));
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.setDialogTitle("select mapper.java path");
                jFileChooser.showDialog(new JLabel(),"select");
                File file = jFileChooser.getSelectedFile();
                if(file == null){
                    return;
                }
                textFieldMapperJavaPath.setText(file.getAbsolutePath());
                if(CommonUtil.isNullOrEmpty(textFieldMapperXmlPath.getText())){
                    textFieldMapperXmlPath.setText(file.getAbsolutePath());
                }
                if(CommonUtil.isNullOrEmpty(textFieldModelPath.getText())){
                    textFieldModelPath.setText(file.getAbsolutePath());
                }
            }
        });
        mapperXmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String defaultFilePath;
                if(!CommonUtil.isNullOrEmpty(textFieldMapperXmlPath.getText())){
                    defaultFilePath = textFieldMapperXmlPath.getText();
                }else if(!CommonUtil.isNullOrEmpty(textFieldMapperJavaPath.getText())){
                    defaultFilePath = textFieldMapperJavaPath.getText();
                }else if(!CommonUtil.isNullOrEmpty(textFieldModelPath.getText())){
                    defaultFilePath = textFieldModelPath.getText();
                }else {
                    defaultFilePath = projectPath;
                }
                JFileChooser jFileChooser = new JFileChooser(new File(defaultFilePath));
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.setDialogTitle("select Mapper.xml path");
                jFileChooser.showDialog(new JLabel(),"select");
                File file = jFileChooser.getSelectedFile();
                if(file == null){
                    return;
                }
                textFieldMapperXmlPath.setText(file.getAbsolutePath());
                if(CommonUtil.isNullOrEmpty(textFieldMapperJavaPath.getText())){
                    textFieldMapperJavaPath.setText(file.getAbsolutePath());
                }
                if(CommonUtil.isNullOrEmpty(textFieldModelPath.getText())){
                    textFieldModelPath.setText(file.getAbsolutePath());
                }
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
                String modelPath = textFieldModelPath.getText();
                String mapperJavaPath = textFieldMapperJavaPath.getText();
                String mapperXmlPath = textFieldMapperXmlPath.getText();
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
                if(CommonUtil.isNullOrEmpty(modelPath)){
                    messageLabel.setText("ModelPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(mapperJavaPath)){
                    messageLabel.setText("MapperJavaPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(mapperXmlPath)){
                    messageLabel.setText("MapperXmlPath required");
                    return;
                }
                if(CommonUtil.isNullOrEmpty(author)){
                    textFieldAuthor.setText("default");
                    author = "default";
                }

                ConfigModel configModel = new ConfigModel();
                configModel.setJdbcHost(jdbcHost);
                configModel.setJdbcDatabase(jdbcDatabase);
                configModel.setJdbcUserName(jdbcUserName);
                configModel.setJdbcPassword(jdbcPassword);
                configModel.setTableName(tableName);
                configModel.setModelPath(modelPath);
                configModel.setMapperJavaPath(mapperJavaPath);
                configModel.setMapperXmlPath(mapperXmlPath);
                configModel.setAuthor(author);
                configModel.setGenerateModel(modelCheckBox.isSelected());
                configModel.setGenerateMapper(mapperCheckBox.isSelected());
                propertiesComponent.setValue("jdbcHost",jdbcHost);
                propertiesComponent.setValue("jdbcDatabase",jdbcDatabase);
                propertiesComponent.setValue("jdbcUserName",jdbcUserName);
                propertiesComponent.setValue("jdbcPassword",jdbcPassword);
                propertiesComponent.setValue("tableName",tableName);
                propertiesComponentProject.setValue("modelPath",modelPath);
                propertiesComponentProject.setValue("mapperJavaPath",mapperJavaPath);
                propertiesComponentProject.setValue("mapperXmlPath",mapperXmlPath);
                propertiesComponent.setValue("author",author);
                try{
                    new CodeGenerate().generate(configModel);
                    messageLabel.setText("success!!!");
                }catch (MyException myException){
                    messageLabel.setText(myException.getMeg());
                    LOGGER.info(myException);
                }

            }
        });
    }
}
