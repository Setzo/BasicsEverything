package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

public class MessagePanel extends JPanel{
	
	private static final long serialVersionUID = -4998576709728752117L;
	
	private JTree serverTree;
	private DefaultTreeCellRenderer treeRenderer;
	
	protected MessagePanel() {

		treeRenderer = new DefaultTreeCellRenderer();
		
		treeRenderer.setLeafIcon(Uti.createIcon("/images/database20.png", null));
		treeRenderer.setOpenIcon(Uti.createIcon("/images/minusServer.png", null));
		treeRenderer.setClosedIcon(Uti.createIcon("/images/plusServer.png", null));
		
		serverTree = new JTree(createTree());
		serverTree.setCellRenderer(treeRenderer);
		
		serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		serverTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				
				try {
					Object userObject = 
							((DefaultMutableTreeNode)serverTree.getLastSelectedPathComponent()).getUserObject();
				
					if(userObject instanceof ServerInfo) {
						System.out.println(((ServerInfo)userObject).getId());
					}
				} catch (Exception ex) {
				}
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(serverTree), BorderLayout.CENTER);
	}
	
	private DefaultMutableTreeNode createTree() {
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
		
		DefaultMutableTreeNode branch0 = new DefaultMutableTreeNode("Poland");
		
		branch0.add(new DefaultMutableTreeNode(new ServerInfo(0, "Reda")));
		branch0.add(new DefaultMutableTreeNode(new ServerInfo(1, "Gdynia")));
		branch0.add(new DefaultMutableTreeNode(new ServerInfo(2, "Gdansk")));
		
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("UK");
		
		branch1.add(new DefaultMutableTreeNode(new ServerInfo(3, "London")));
		branch1.add(new DefaultMutableTreeNode(new ServerInfo(4, "Edinburgh")));
		
		top.add(branch0);
		top.add(branch1);
		
		return top;
	}
}
