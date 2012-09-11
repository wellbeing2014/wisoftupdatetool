﻿/*
 * 由SharpDevelop创建。
 * 用户： wellbeing
 * 日期: 2012/9/11
 * 时间: 20:51
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using System.Collections.Generic;
namespace NewWisoftUpdateTool.ui
{
	/// <summary>
	/// Description of MainProTree.
	/// </summary>
	public partial class MainProTree : UserControl
	{
		private List<string> wifilelist=new List<string>{"mypackage.wi","行政许可(aims)5.1.1.wi"};
		
		public List<string> Wifilelist {
			get { return wifilelist; }
			set { wifilelist = value; }
		}
		public MainProTree()
		{
			//
			// The InitializeComponent() call is required for Windows Forms designer support.
			//
			InitializeComponent();
			LoadWifile();
			//
			// TODO: Add constructor code after the InitializeComponent() call.
			//
		}
		
		public void LoadWifile()
		{
			ImageList il = new ImageList();
			il.Images.Add("selectPackfile",global::NewWisoftUpdateTool.Resource.check_boxes);
			il.Images.Add("configProperty",global::NewWisoftUpdateTool.Resource.preferences);
			il.Images.Add("manualConfig",global::NewWisoftUpdateTool.Resource.paper_content_pencil_48);
			il.Images.Add("perviewAll",global::NewWisoftUpdateTool.Resource.preview);
			il.Images.Add("editSql",global::NewWisoftUpdateTool.Resource.database);
			this.treeView1.ImageList = il;
			for (int i = 0; i < wifilelist.Count; i++) {
				TreeNode fileroot = new TreeNode(wifilelist[i]);
				fileroot.ImageKey="configProperty";
				fileroot.SelectedImageKey="configProperty";
				
				TreeNode selectPackfile = new TreeNode("选择打包文件");
				selectPackfile.ImageKey="selectPackfile";
				selectPackfile.SelectedImageKey="selectPackfile";
				
				TreeNode manualConfig = new TreeNode("手动修改配置");
				manualConfig.ImageKey="manualConfig";
				manualConfig.SelectedImageKey="manualConfig";
				
				TreeNode editSql = new TreeNode("编写SQL语句");
				editSql.ImageKey="editSql";
				editSql.SelectedImageKey="editSql";
				
				fileroot.Nodes.Add(selectPackfile);
				fileroot.Nodes.Add(manualConfig);
				fileroot.Nodes.Add(editSql);
				
				this.treeView1.Nodes.Add(fileroot);
			}
		}
		
		void TreeView1MouseDown(object sender, MouseEventArgs e)
		{
			if(e.Button== MouseButtons.Right)
			{
				 this.treeView1.ContextMenuStrip = this.contextMenuStrip1;
			}
		}
	}
}
