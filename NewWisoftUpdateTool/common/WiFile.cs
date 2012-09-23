/*
 * 由SharpDevelop创建。
 * 用户： wellbeing
 * 日期: 2012/9/23
 * 时间: 10:51
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
using System;

namespace NewWisoftUpdateTool.common
{
	/// <summary>
	/// Description of WiFile.
	/// </summary>
	public class WiFile
	{
		private string _Filename;
		
		public string Filename {
			get { return _Filename; }
			set { _Filename = value; }
		}
		private string _fullPath;
		
		public string FullPath {
			get { return _fullPath; }
			set { _fullPath = value; }
		}
		private string _tempMD5;
		
		public string TempMD5 {
			get { return _tempMD5; }
			set { _tempMD5 = value; }
		}
		
		public WiFile(string name)
		{
			this._Filename = name;
		}
		
		public void rename(string newname)
		{
			this._Filename = newname;
		}
	}
}
