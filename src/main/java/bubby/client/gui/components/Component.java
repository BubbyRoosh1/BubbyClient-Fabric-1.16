package bubby.client.gui.components;

import net.minecraft.client.util.math.MatrixStack;

public interface Component
{
  void renderComponent(MatrixStack m);

  void updateComponent(int x, int y);

  void mouseClicked(int x, int y, int b);

  void mouseReleased(int x, int y, int b);

  int getParentHeight();

  void keyTyped(char c, int k);

  void setOff(int nOff);

  int getHeight();

  void handleMouseInput();
}
