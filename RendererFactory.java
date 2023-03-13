/**
 * The Renderer Factory will be responsible for creating the appropriate rendering interface.
 */
public class RendererFactory {

    /**
     * Responsible for mapping the string from the command line to the board's visibility state object
     * @param type none or console
     * @param size the size of board
     * @return Renderer
     */
    public Renderer buildRenderer(String type, int size){
        switch (type){

            case "console":
                return new ConsoleRenderer(size);

            case "none":
                return new VoidRenderer();

            default:
                return null;
        }
    }
}
