import javax.sound.midi.*;

public class MusicTest1 {
    public static void main(String[] args) {
        MusicTest1 mt = new MusicTest1();
        if(args.length<2) {
            System.out.println("Don't forget the instrument and note args");
        }else{
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mt.play(instrument, note);
        }
    }
    public void play(int instrument, int note) {
        try {
            //get the sequencer and open it
            Sequencer player = MidiSystem.getSequencer();         
            player.open();
            
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            //ask the sequence for a track. Remember, the Track lives in sequence,
            //and the MIDI data lives in the track.
            Track track = seq.createTrack();

            MidiEvent event = null;

            //Put some MIDI events into the track

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);
            
            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);
            
            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);

            player.setSequence(seq);
            player.start();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}