import constants

def write_output(output_text, output_file_name = constants.OUTPUT_PATH):
    """ Writes a string to a file at a given path. """
    with open(output_file_name,"w+") as output_file:
        output_file.write(output_text)
