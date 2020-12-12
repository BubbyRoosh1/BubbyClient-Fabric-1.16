package bubby.client.gui;

import bubby.api.command.CommandManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class CommandScreen extends Screen
{
  private TextFieldWidget commandInput;

  public CommandScreen()
  {
    super(new LiteralText("Command Screen"));
  }

  @Override
  public void
  init()
  {
    commandInput = new TextFieldWidget(textRenderer, 5, 5, width - 10, 15, LiteralText.EMPTY);
  }

  @Override
  public void
  render(MatrixStack m, int mx, int my, float delta)
  {
    commandInput.render(m, mx, my, delta);
    super.render(m, mx, my, delta);
  }

  @Override
  public boolean
  charTyped(char c, int cc)
  {
    commandInput.charTyped(c, cc);
    return super.charTyped(c, cc);
  }

  @Override
  public boolean
  keyPressed(int key, int scan, int mod)
  {
    if(key == 257)
    {
      try
      {
        CommandManager.runCommand(commandInput.getText());
      }
      catch(Exception e)
      {
      }
      commandInput.setText("");
    }
    commandInput.keyPressed(key, scan, mod);
    return super.keyPressed(key, scan, mod);
  }

  @Override
  public void
  tick()
  {
    commandInput.setSelected(true);
    commandInput.tick();
  }

  @Override
  public boolean
  isPauseScreen()
  {
    return false;
  }
}
