/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halen3.GUI.Film;

import static Halen3.GUI.Film.FilmGUI.RetrievedListEmptyPanel;
import static Halen3.GUI.Film.FilmGUI.RetrievedListScroll;
import static Halen3.GUI.Film.FilmGUI.delete;
import static Halen3.GUI.Film.FilmGUI.filmPanel;
import static Halen3.GUI.Film.FilmGUI.moveToFolderText;
import static Halen3.GUI.Film.FilmGUI.ruleNameInput;
import static Halen3.GUI.Film.FilmGUI.rulesListPanel;
import static Halen3.GUI.Film.FilmGUI.rulesScroll;
import static Halen3.GUI.Film.FilmGUI.run;
import static Halen3.GUI.Film.FilmGUI.save;
import static Halen3.GUI.Film.FilmGUI.search;
import static Halen3.GUI.Film.FilmGUI.searchForText;
import static Halen3.GUI.Film.FilmGUI.searchInFolderText;
import static Halen3.GUI.Film.FilmGUI.traktURLInput;
import static Halen3.GUI.Film.FilmGUI.update;
import Halen3.GUI.GUIBase;
import static Halen3.GUI.GUIBase.tertiary;
import Halen3.GUI.NoticePanels.LoggingPanel;
import static Halen3.GUI.NoticePanels.SavingPanel.savePanel;
import Halen3.GUI.RuleManager;
import Halen3.IO.FileManager;
import Halen3.Retrievers.Films.DownloadNewFilms;
import static Halen3.Retrievers.Films.Trakt.UpdateTraktData.currentlyUpdatingFilmRules;
import Halen3.Retrievers.TvShows.DownloadNewEpisodes;
import static Halen3.Retrievers.TvShows.Trakt.UpdateTraktData.currentlyUpdatingTVRules;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author brenn
 */
public class FilmGUIControls
{
    public static void initFilmControls()
    {
             ruleNameInput.addMouseListener(
                new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e
            )
            {
                //only blank if the text is the default instructions
                if (ruleNameInput.getText().equals("..."))
                {
                    ruleNameInput.setText("");
                }
            }
        }
        );

        search.addMouseListener(
                new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e
            )
            {
                //only blank if the text is the default instructions
                if (search.getText().equals("..."))
                {
                    search.setText("");
                }
            }
        }
        );

        traktURLInput.addMouseListener(
                new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e
            )
            {
                //only blank if the text is the default instructions
                if (traktURLInput.getText().equals("..."))
                {
                    traktURLInput.setText("");
                }
            }
        }
        );

        searchInFolderText.addMouseListener(
                new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e
            )
            {
                //only blank if the text is the default instructions
                if (searchInFolderText.getText().equals("..."))
                {
                    searchInFolderText.setText("");
                }
            }
        }
        );

        moveToFolderText.addMouseListener(
                new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e
            )
            {
                //only blank if the text is the default instructions
                if (moveToFolderText.getText().equals("..."))
                {
                    moveToFolderText.setText("");
                }
            }
        }
        );

        searchForText.addMouseListener(
                new MouseAdapter()
        {

            @Override
            public void mouseClicked(MouseEvent e
            )
            {
                //only blank if the text is the default instructions
                if (searchForText.getText().equals("..."))
                {
                    searchForText.setText("");
                }
            }
        }
        );

    
  
        
                    save.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent me)
            {
             
                GUIBase.hideButtons();
                filmPanel.setVisible(false);
                    savePanel.setVisible(true);
              RuleManager.saveFilmRule(ruleNameInput.getText().trim());
               filmPanel.setVisible(true);
                    savePanel.setVisible(false);
                    GUIBase.showButtons();
              
    
            }

            @Override
            public void mousePressed(MouseEvent me
            )
            {
                ImageIcon s = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/savePressed.png"));
                ImageIcon ss = new ImageIcon(s.getImage().getScaledInstance(save.getWidth(), save.getHeight(), java.awt.Image.SCALE_DEFAULT));
                save.setIcon(ss);
            }

            @Override
            public void mouseReleased(MouseEvent me
            )
            {
                ImageIcon s = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/save.png"));
                ImageIcon ss = new ImageIcon(s.getImage().getScaledInstance(save.getWidth(), save.getHeight(), java.awt.Image.SCALE_DEFAULT));
                save.setIcon(ss);
            }

            @Override
            public void mouseEntered(MouseEvent me
            )
            {
                ImageIcon s = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/saveHover.png"));
                ImageIcon ss = new ImageIcon(s.getImage().getScaledInstance(save.getWidth(), save.getHeight(), java.awt.Image.SCALE_DEFAULT));
                save.setIcon(ss);
            }

            @Override
            public void mouseExited(MouseEvent me
            )
            {
                ImageIcon s = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/save.png"));
                ImageIcon ss = new ImageIcon(s.getImage().getScaledInstance(save.getWidth(), save.getHeight(), java.awt.Image.SCALE_DEFAULT));
                save.setIcon(ss);
            }
        }
        );
            
                 delete.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent me)
            {

                if(!ruleNameInput.getText().trim().equals("") & !ruleNameInput.getText().trim().equals("..."))
                {
                int opcion = JOptionPane.showConfirmDialog(null, "Do you wish to delete this rule?", "Delete Rule", JOptionPane.YES_NO_OPTION);

                if (opcion == 0)
                { //The ISSUE is here
                    
                    File im = new File(FileManager.returnTag("image", FileManager.readFile(Halen3.IO.FileManager.launchPath() + "/rules/films/" + ruleNameInput.getText().trim() + ".xml").getItem(0)));
                    im.delete();
                    
                    File del = new File(Halen3.IO.FileManager.launchPath() + "/rules/films/" + ruleNameInput.getText().trim() + ".xml");
                    del.delete();
                
                    //blank rules in ui
            rulesListPanel.removeAll();
            rulesListPanel.repaint();
            rulesScroll.revalidate();
            rulesScroll.repaint();

            FilmGUI.refreshFilmPanel();

            rulesListPanel.revalidate();
            rulesListPanel.repaint();
            rulesScroll.revalidate();
            rulesScroll.repaint();

            ruleNameInput.setText("...");
            search.setText("...");
            traktURLInput.setText("...");
            searchInFolderText.setText("...");
            moveToFolderText.setText("...");
            searchForText.setText("...");

        RetrievedListEmptyPanel.revalidate();
      RetrievedListEmptyPanel.repaint();
          RetrievedListScroll.revalidate();
        RetrievedListScroll.repaint();
                } else
                {
                    
                }
                }
              //  Delete.deleteRule();

            }

            @Override
            public void mousePressed(MouseEvent me)
            {
                ImageIcon d = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/deletePressed.png"));
                ImageIcon ds = new ImageIcon(d.getImage().getScaledInstance(delete.getWidth(), delete.getHeight(), java.awt.Image.SCALE_DEFAULT));
                delete.setIcon(ds);
            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
                ImageIcon d = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/delete.png"));
                ImageIcon ds = new ImageIcon(d.getImage().getScaledInstance(delete.getWidth(), delete.getHeight(), java.awt.Image.SCALE_DEFAULT));
                delete.setIcon(ds);
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
                ImageIcon d = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/deleteHover.png"));
                ImageIcon ds = new ImageIcon(d.getImage().getScaledInstance(delete.getWidth(), delete.getHeight(), java.awt.Image.SCALE_DEFAULT));
                delete.setIcon(ds);
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
                ImageIcon d = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/delete.png"));
                ImageIcon ds = new ImageIcon(d.getImage().getScaledInstance(delete.getWidth(), delete.getHeight(), java.awt.Image.SCALE_DEFAULT));
                delete.setIcon(ds);
            }
        });
                  
      run.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent me)
            {
                
               
              //  DownloadNewEpisodes.saveResults = false;
                  LoggingPanel.showFilmLogging();
                SwingWorker myWorker = new SwingWorker<String, Void>()
                {
                    @Override
                    protected String doInBackground() throws Exception
                    {
                         DownloadNewFilms.downloadNewFilms();
                        
                        LoggingPanel.hideFilmLogging();
                        GUIBase.frame.revalidate();
                        GUIBase.frame.repaint();
                        return null;
                    }
                };
                myWorker.execute();
//                System.out.println("RUNNING RULES");
//
////                Thread run = new Thread(new Runnable()
////                {
////                    @Override
////                    public void run()
////                    {
//                try
//                {
//                    runningRule.setVisible(true);
//                    progressBar.setVisible(true);
//                    anim.setVisible(true);
//                    rulesPane.setVisible(false);
//                    save.setVisible(false);
//                    GUI.trakt.setVisible(false);
//                    cb.setVisible(false);
//                    GUI.run.setVisible(false);
//                    ruleName.setVisible(false);
//                    name.setVisible(false);
//                    search.setVisible(false);
//                    searchIn.setVisible(false);
//                    RetrievedListPane.setVisible(false);
//                    delete.setVisible(false);
//                    inputsPane.setVisible(false);
//                    //                      start.setVisible(false);
//                    //                    endPoint.setVisible(false);
//                    settings.setVisible(false);
//                    setTheme.setVisible(false);
//                    GUI.updateRulesData.setVisible(false);
//                    Run.runRules();
//
//                } catch (IOException | URISyntaxException ex)
//                {
//                    JOptionPane.showMessageDialog(null, "Rules failed to run, unknown cause", "ERROR", JOptionPane.WARNING_MESSAGE);
//                }

//                    }
//                });
//                run.start();
            }

            @Override
            public void mousePressed(MouseEvent me)
            {
                ImageIcon r = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/runPressed.png"));
                ImageIcon rs = new ImageIcon(r.getImage().getScaledInstance(run.getWidth(), run.getHeight(), java.awt.Image.SCALE_DEFAULT));
                run.setIcon(rs);
            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
                ImageIcon r = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/run.png"));
                ImageIcon rs = new ImageIcon(r.getImage().getScaledInstance(run.getWidth(), run.getHeight(), java.awt.Image.SCALE_DEFAULT));
                run.setIcon(rs);
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
                ImageIcon r = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/runHover.png"));
                ImageIcon rs = new ImageIcon(r.getImage().getScaledInstance(run.getWidth(), run.getHeight(), java.awt.Image.SCALE_DEFAULT));
                run.setIcon(rs);
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
                ImageIcon r = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/run.png"));
                ImageIcon rs = new ImageIcon(r.getImage().getScaledInstance(run.getWidth(), run.getHeight(), java.awt.Image.SCALE_DEFAULT));
                run.setIcon(rs);
            }
        });
      
    update.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent me)
            {
                
                LoggingPanel.showFilmLogging();
                SwingWorker myWorker = new SwingWorker<String, Void>()
                {
                    @Override
                    protected String doInBackground() throws Exception
                    {
                        Halen3.Retrievers.Films.Trakt.UpdateTraktData.updateFilmRules();
                        
                        do
                        {
                            Thread.sleep(1000);
                        }while(currentlyUpdatingFilmRules == true);
                        LoggingPanel.hideTVLogging();
                        return null;
                    }
                };
                myWorker.execute();

            }

            @Override
            public void mousePressed(MouseEvent me)
            {
                ImageIcon t = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/refreshPressed.png"));
                ImageIcon ts = new ImageIcon(t.getImage().getScaledInstance(update.getWidth(), update.getHeight(), java.awt.Image.SCALE_DEFAULT));
                update.setIcon(ts);

            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
                ImageIcon t = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/refresh.png"));
                ImageIcon ts = new ImageIcon(t.getImage().getScaledInstance(update.getWidth(), update.getHeight(), java.awt.Image.SCALE_DEFAULT));
                update.setIcon(ts);
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
                ImageIcon t = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/refreshHover.png"));
                ImageIcon ts = new ImageIcon(t.getImage().getScaledInstance(update.getWidth(), update.getHeight(), java.awt.Image.SCALE_DEFAULT));
                update.setIcon(ts);

            }

            @Override
            public void mouseExited(MouseEvent me)
            {
                ImageIcon t = new ImageIcon(GUIBase.color(tertiary, "Resources/metro/buttons/refresh.png"));
                ImageIcon ts = new ImageIcon(t.getImage().getScaledInstance(update.getWidth(), update.getHeight(), java.awt.Image.SCALE_DEFAULT));
                update.setIcon(ts);

            }
        });
    }
}
