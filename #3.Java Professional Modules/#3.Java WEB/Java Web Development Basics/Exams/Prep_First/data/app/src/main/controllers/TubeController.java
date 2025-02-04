package main.controllers;

import main.entities.Tube;
import main.models.binding.TubeAddBindingModel;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.summermvc.api.*;
import main.repositories.TubeRepository;
import main.repositories.UserRepository;

@Controller
public class TubeController extends BaseController {
    private UserRepository userRepository;
    private TubeRepository tubeRepository;

    public TubeController() {
        this.userRepository = new UserRepository();
        this.tubeRepository = new TubeRepository();
    }

    @GetMapping(route = "/upload-tube")
    public String uploadTube(HttpSoletRequest request, Model model) {
        if(request.getSession()!=null && request.getSession().isValid()) {
            return super.redirect(request, model, "upload");
        }else{
            return super.redirect(request, model, "");
        }
    }

    @PostMapping(route = "/upload-tube")
    public String uploadTubeConfirm(HttpSoletRequest request, Model model, TubeAddBindingModel tubeAddBindingModel) {
        String currentUserId = request.getSession().getAttributes().get("user-id").toString();

        if (tubeAddBindingModel == null || tubeAddBindingModel.getTitle() == null
                || tubeAddBindingModel.getAuthor() == null
                || tubeAddBindingModel.getDescription() == null
                || tubeAddBindingModel.getYoutubeId() == null) {
            return super.view(request, model, "/upload-tube");
        }
        Tube tube = new Tube();
        tube.setTitle(tubeAddBindingModel.getTitle());
        tube.setAuthor(tubeAddBindingModel.getAuthor());
        tube.setYoutubeId(tubeAddBindingModel.getYoutubeId());
        tube.setDescription(tubeAddBindingModel.getDescription());
        tube.setViews(0);
        tube.setUploader(this.userRepository.findById(currentUserId));

        this.tubeRepository.createTube(tube);

        return super.redirect(request, model, "home");
    }

    @GetMapping(route = "/tube/details/{id}")
    public String tubeDetails(@PathVariable(name = "id") String id, HttpSoletRequest request, Model model) {
        if(request.getSession()!=null && request.getSession().isValid()) {
            this.tubeRepository.incrementViews(id);
            Tube tube = this.tubeRepository.findById(id);

            if (tube == null) {
                return super.redirect(request, model, "home");
            }

            model.addAttribute("title", tube.getTitle());
            model.addAttribute("author", tube.getAuthor());
            model.addAttribute("views", tube.getViews());
            model.addAttribute("iFrameUrl", tube.extractIFrameUrl());
            model.addAttribute("description", tube.getDescription());


            return super.view(request, model, "details");
        }else{
            return super.redirect(request,model,"");
        }
    }
}
