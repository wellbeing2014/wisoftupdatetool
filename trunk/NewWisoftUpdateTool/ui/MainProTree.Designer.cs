/*
 * 由SharpDevelop创建。
 * 用户： wellbeing
 * 日期: 2012/9/11
 * 时间: 20:51
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
namespace NewWisoftUpdateTool.ui
{
	partial class MainProTree
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		
		/// <summary>
		/// Disposes resources used by the control.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing) {
				if (components != null) {
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}
		
		/// <summary>
		/// This method is required for Windows Forms designer support.
		/// Do not change the method contents inside the source code editor. The Forms designer might
		/// not be able to load this method if it was changed manually.
		/// </summary>
		private void InitializeComponent()
		{
			this.components = new System.ComponentModel.Container();
			this.panel1 = new System.Windows.Forms.Panel();
			this.treeView1 = new System.Windows.Forms.TreeView();
			this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
			this.newMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.deleteMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.deleteAllMenuTtem = new System.Windows.Forms.ToolStripMenuItem();
			this.saveMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.saveAllMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.saveByPathMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.panel1.SuspendLayout();
			this.contextMenuStrip1.SuspendLayout();
			this.SuspendLayout();
			// 
			// panel1
			// 
			this.panel1.Controls.Add(this.treeView1);
			this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
			this.panel1.Location = new System.Drawing.Point(0, 0);
			this.panel1.Name = "panel1";
			this.panel1.Size = new System.Drawing.Size(241, 359);
			this.panel1.TabIndex = 0;
			// 
			// treeView1
			// 
			this.treeView1.Dock = System.Windows.Forms.DockStyle.Fill;
			this.treeView1.ItemHeight = 20;
			this.treeView1.Location = new System.Drawing.Point(0, 0);
			this.treeView1.Name = "treeView1";
			this.treeView1.ShowNodeToolTips = true;
			this.treeView1.Size = new System.Drawing.Size(241, 359);
			this.treeView1.TabIndex = 0;
			this.treeView1.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.TreeView1AfterSelect);
			this.treeView1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.TreeView1MouseDown);
			// 
			// contextMenuStrip1
			// 
			this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
									this.newMenuItem,
									this.deleteMenuItem,
									this.deleteAllMenuTtem,
									this.saveMenuItem,
									this.saveAllMenuItem,
									this.saveByPathMenuItem});
			this.contextMenuStrip1.Name = "contextMenuStrip1";
			this.contextMenuStrip1.Size = new System.Drawing.Size(153, 158);
			// 
			// newMenuItem
			// 
			this.newMenuItem.Name = "newMenuItem";
			this.newMenuItem.Size = new System.Drawing.Size(152, 22);
			this.newMenuItem.Text = "新建";
			// 
			// deleteMenuItem
			// 
			this.deleteMenuItem.Name = "deleteMenuItem";
			this.deleteMenuItem.Size = new System.Drawing.Size(152, 22);
			this.deleteMenuItem.Text = "删除";
			// 
			// deleteAllMenuTtem
			// 
			this.deleteAllMenuTtem.Name = "deleteAllMenuTtem";
			this.deleteAllMenuTtem.Size = new System.Drawing.Size(152, 22);
			this.deleteAllMenuTtem.Text = "删除全部";
			// 
			// saveMenuItem
			// 
			this.saveMenuItem.Name = "saveMenuItem";
			this.saveMenuItem.Size = new System.Drawing.Size(152, 22);
			this.saveMenuItem.Text = "保存";
			// 
			// saveAllMenuItem
			// 
			this.saveAllMenuItem.Name = "saveAllMenuItem";
			this.saveAllMenuItem.Size = new System.Drawing.Size(152, 22);
			this.saveAllMenuItem.Text = "保存全部";
			// 
			// saveByPathMenuItem
			// 
			this.saveByPathMenuItem.Name = "saveByPathMenuItem";
			this.saveByPathMenuItem.Size = new System.Drawing.Size(152, 22);
			this.saveByPathMenuItem.Text = "另存为……";
			// 
			// MainProTree
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.Controls.Add(this.panel1);
			this.Name = "MainProTree";
			this.Size = new System.Drawing.Size(241, 359);
			this.panel1.ResumeLayout(false);
			this.contextMenuStrip1.ResumeLayout(false);
			this.ResumeLayout(false);
		}
		private System.Windows.Forms.ToolStripMenuItem saveByPathMenuItem;
		private System.Windows.Forms.ToolStripMenuItem saveAllMenuItem;
		private System.Windows.Forms.ToolStripMenuItem deleteAllMenuTtem;
		private System.Windows.Forms.ToolStripMenuItem newMenuItem;
		private System.Windows.Forms.ToolStripMenuItem saveMenuItem;
		private System.Windows.Forms.ToolStripMenuItem deleteMenuItem;
		private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
		private System.Windows.Forms.TreeView treeView1;
		private System.Windows.Forms.Panel panel1;
	}
}
